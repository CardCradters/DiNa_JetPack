package com.example.dina_compose.screen.scan

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.dina_compose.component.BottomBar
import com.example.dina_compose.component.BottomSheet
import com.example.dina_compose.component.TopAppBar
import com.example.dina_compose.data.ProfileRequest
import com.example.dina_compose.screen.home.HomeViewModel
import com.example.dina_compose.screen.profile.ProfileViewModel
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Scan(navController: NavHostController,
         viewModel: ProfileViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
  var barcodeText by remember { mutableStateOf("") }
  var imageUri by remember { mutableStateOf<Uri?>(null) }
  val scaffoldState = rememberScaffoldState()
  val sheetState = rememberBottomSheetScaffoldState()
  val coroutineScope = rememberCoroutineScope()
  val contextForToast = LocalContext.current
  val context = LocalContext.current
  val profiles: ProfileRequest? by viewModel.profile.collectAsState()
  val loadingState = remember { mutableStateOf(false) }
  var openDialog by remember { mutableStateOf(false) }

  LaunchedEffect(Unit) {
    loadingState.value = true // Memperbarui state loading sebelum memuat data
    viewModel.fetchProfile(context)
    loadingState.value = false // Memperbarui state loading setelah selesai memuat data
  }

  val galleryLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
    if (uri != null) {
      imageUri = uri

    }
  }

  val imagePainter = rememberImagePainter(data = imageUri)


  Scaffold(
    scaffoldState = scaffoldState,
    topBar = {
      TopAppBar {

        coroutineScope.launch {
          if (sheetState.bottomSheetState.isCollapsed)
          {
            sheetState.bottomSheetState.expand()
          } else
          {
            sheetState.bottomSheetState.collapse()
          }
        }
      }
    },
    bottomBar = {
      BottomBar(
        contextForToast = contextForToast, navController = navController,
        onShareClicked = { openDialog = true }, viewModel = ProfileViewModel
          (savedStateHandle = SavedStateHandle())
      )
    },
  ){ innerPadding ->
    BottomSheetScaffold(
      scaffoldState = sheetState,
      sheetBackgroundColor = MaterialTheme.colors.primary,
      sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
      sheetContent = {
        BottomSheet(
          coroutineScope = coroutineScope,
          scaffoldState = sheetState,
          contextForToast = contextForToast,viewModel = HomeViewModel(), navController=
          navController
        )
      },
      content = {
        Column(
          modifier = Modifier
            .padding(innerPadding)
            .padding(horizontal = 24.dp),
          verticalArrangement = Arrangement.Center,
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
          ) {
            Row() {
              Button(
                onClick = {
                  galleryLauncher.launch("image/*")
                },
                colors = ButtonDefaults.buttonColors(Color(0xFFFFFFFF)),
                modifier = Modifier.padding(16.dp)
              ) {
                Text(text = "Select Image")
              }

              Spacer(modifier = Modifier.width(7.dp))

              Button(
                onClick = {
                  navController.navigate("camera_preview")
                },
                colors = ButtonDefaults.buttonColors(Color(0xFFFFFFFF)),
                modifier = Modifier.padding(16.dp)
              ) {
                Text(text = "Open Camera")
              }
            }

            if (loadingState.value) {
              LinearProgressIndicator(modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF83B9E2)))
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
                profiles?.let { profile ->
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
                          if (!rawValue.isNullOrBlank()) {
                            val uid = rawValue.substringBefore("/")
                            val name = rawValue.substringAfter("/")
                            val destination = "detail_screen/$uid/$name"
                            navController.navigate(destination) {
                              popUpTo("scan_screen") {
                                inclusive = true
                              }
                            }
                            return@addOnSuccessListener
                          }
                        }
                        barcodeText = "No barcode found."
                      }
                      .addOnFailureListener {
                        barcodeText = "Failed to scan barcode."
                      }
                  }
                }
              },
              modifier = Modifier.padding(16.dp)
            ) {
              Text(text = "Scan")
            }


          }
}})}}


