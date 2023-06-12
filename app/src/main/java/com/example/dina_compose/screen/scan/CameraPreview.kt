package com.example.dina_compose.screen.scan


import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun CameraPreview(navController: NavController) {
  val context = LocalContext.current
  val imageUri = remember { mutableStateOf<Uri?>(null) }
  val barcodeText = remember { mutableStateOf("") }

  val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
    if (isSuccess) {
      val imageFile = createImageFile(context)
      val uri = FileProvider.getUriForFile(context, "${context.packageName}.provider", imageFile)
      imageUri.value = uri
    }
  }



  Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
  AndroidView(factory = { ctx ->
    val previewView = PreviewView(ctx)
    val cameraProviderFuture = ProcessCameraProvider.getInstance(ctx)

    cameraProviderFuture.addListener({
      val cameraProvider = cameraProviderFuture.get()
      val preview = Preview.Builder()
        .build()
        .also {
          it.setSurfaceProvider(previewView.surfaceProvider)
        }

      val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

      try {
        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(
          context as LifecycleOwner,
          cameraSelector,
          preview
        )
      } catch (exc: Exception) {
        // Handle error
      }
    }, ContextCompat.getMainExecutor(ctx))

    previewView
  },  modifier = Modifier.fillMaxSize())

  // Release the camera when the composable is disposed
  DisposableEffect(Unit) {
    onDispose {
      // Release the camera resources
      val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
      cameraProviderFuture.addListener({
        val cameraProvider = cameraProviderFuture.get()
        cameraProvider.unbindAll()
      }, ContextCompat.getMainExecutor(context))
    }
  }

    Button(
      onClick = {
        cameraLauncher.launch(imageUri.value)
      },
      colors = ButtonDefaults.buttonColors(Color(0xFF83B9E2)),
      modifier = Modifier.padding(16.dp)
    ) {
      Text(text = "Scan Barcode")
    }

    Spacer(modifier = Modifier.height(16.dp))

    Text(
      text = barcodeText.value,
      fontSize = 20.sp,
      modifier = Modifier.padding(16.dp)
    )
}}

@Throws(IOException::class)
fun createImageFile(context: Context): File {
  val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(
    Date()
  )
  val storageDir: File = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    ?: throw IOException("Failed to get external storage directory.")

  if (!storageDir.exists()) {
    storageDir.mkdirs() // Create the directory if it doesn't exist
  }

  return File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir)
}

@androidx.compose.ui.tooling.preview.Preview
@Composable
fun vieeww(){
  val context = LocalContext.current
  CameraPreview(navController = NavController(context))
}

