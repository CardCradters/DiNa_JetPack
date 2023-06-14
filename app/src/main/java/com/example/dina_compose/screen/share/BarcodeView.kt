package com.example.dina_compose.screen.share

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.dina_compose.data.ProfileRequest
import com.example.dina_compose.screen.profile.ProfileViewModel

 @Composable
fun BarcodeView(
   data: String?,
  onDismiss: () -> Unit,
  viewModel: ProfileViewModel
) {
  val profile: ProfileRequest? by viewModel.profile.collectAsState(null)
  val contextForToast = LocalContext.current
  LaunchedEffect(Unit) {
    viewModel.fetchProfile(contextForToast)
  }

  val qrCode = buildString {
    profile?.let { p ->
      append(p.uid)
    }
  }

  val barcodeBitmap: Bitmap? = remember(qrCode) {
    viewModel.generateQRCodeBitmap(qrCode)
  }

  if (profile != null) {
    AlertDialog(
      onDismissRequest = { onDismiss() },
      title = { Text("Barcode") },
      text = {
        barcodeBitmap?.let { bitmap ->
          Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = null,
            modifier = Modifier
              .size(400.dp)
              .padding(top = 16.dp)
          )
        } ?: run {
          Text("Failed to generate barcode.")
        }
      },
      confirmButton = {
        Button(
          onClick = { onDismiss() }
        ) {
          Text("Close")
        }
      }
    )
  }
  }



//
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun BarcodePreview()
//{
//  DiNa_ComposeTheme(darkTheme = false) {
//    Barcode{}
//  }
//}