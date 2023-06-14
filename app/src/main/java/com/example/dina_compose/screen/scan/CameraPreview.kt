package com.example.dina_compose.screen.scan


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.zxing.ResultPoint
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.BarcodeView
@Composable
fun CameraPreview(navController: NavController) {
  val navController = rememberNavController()
  val context = LocalContext.current
  Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    AndroidView(
      factory = { context ->
        BarcodeView(context).apply {
          decodeContinuous(object : BarcodeCallback
          {
            override fun barcodeResult(result: BarcodeResult?) {
              result?.let {
                val barcodeValue = it.text
                Toast.makeText(
                  context,
                  "Scanned barcode: $barcodeValue",
                  Toast.LENGTH_SHORT
                ).show()

                launchImplicitIntent(context, barcodeValue)
              }
            }

            override fun possibleResultPoints(resultPoints: List<ResultPoint>?) {
              // Tidak perlu diimplementasikan untuk sekarang
            }
          })
        }
      },
      modifier = Modifier.fillMaxSize()
    )
  }
}

private fun launchImplicitIntent(context: Context, url: String) {
  val intent = Intent(Intent.ACTION_VIEW)
  intent.data = Uri.parse(url)
  context.startActivity(intent)
}

