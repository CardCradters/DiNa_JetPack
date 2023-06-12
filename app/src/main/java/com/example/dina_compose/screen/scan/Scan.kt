package com.example.dina_compose.screen.scan

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
@Composable
fun Scan(navController: NavController) {
  var barcodeText by remember { mutableStateOf("") }
  var imageUri by remember { mutableStateOf<Uri?>(null) }

  val context = LocalContext.current

  val scanBarcode: () -> Unit = {
    imageUri?.let { uri ->
      val image = InputImage.fromFilePath(context, uri)
      val options = BarcodeScannerOptions.Builder()
        .setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS)
        .build()

      val scanner: BarcodeScanner = BarcodeScanning.getClient(options)

      scanner.process(image)
        .addOnSuccessListener { barcodes ->
          for (barcode in barcodes) {
            val rawValue = barcode.rawValue
            barcodeText = rawValue ?: ""
          }
        }
        .addOnFailureListener {
          barcodeText = "Failed to scan barcode."
        }
    }
  }

  val galleryLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
    if (uri != null) {
      imageUri = uri
    }
  }

  val imagePainter = rememberImagePainter(data = imageUri)

  Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Row() {
      Button(
        onClick = {
          galleryLauncher.launch("image/*")
        },
        colors = ButtonDefaults.buttonColors(Color(0xFF83B9E2)),
        modifier = Modifier.padding(16.dp)
      ) {
        Text(text = "Select Image")
      }

      Spacer(modifier = Modifier.width(10.dp))

      Button(
        onClick = {
          navController.navigate("camera_preview")
        },
        colors = ButtonDefaults.buttonColors(Color(0xFF83B9E2)),
        modifier = Modifier.padding(16.dp)
      ) {
        Text(text = "Scan with Camera")
      }
    }


    Spacer(modifier = Modifier.height(16.dp))

    Image(
      painter = imagePainter,
      contentDescription = "Selected Image",
      contentScale = ContentScale.Fit,
      modifier = Modifier
        .size(200.dp)
        .padding(16.dp)
        .border(2.dp, Color.Gray)
    )

    Spacer(modifier = Modifier.height(16.dp))

    Button(
      onClick = {
        scanBarcode()
      },
      colors = ButtonDefaults.buttonColors(Color(0xFF83B9E2)),
      modifier = Modifier.padding(16.dp)
    ) {
      Text(text = "Scan")
    }

    Spacer(modifier = Modifier.height(16.dp))

    Text(
      text = barcodeText,
      fontSize = 20.sp,
      modifier = Modifier.padding(16.dp)
    )
  }
}



@Preview(showSystemUi = true)
@Composable
fun View(){
  val context = LocalContext.current
  Scan(navController = NavController(context))
}
