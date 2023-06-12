package com.example.dina_compose.screen.share

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.FileProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.dina_compose.data.ProfileRequest
import com.example.dina_compose.screen.profile.ProfileViewModel
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

@Composable
fun ShareBarcode(navController: NavHostController, qrCodeContent: String) {
  val context = LocalContext.current
  val viewModel: ProfileViewModel = viewModel()

  LaunchedEffect(Unit) {
    viewModel.fetchProfile(context)
  }

  val profile: ProfileRequest? by viewModel.profile.collectAsState(null)

  val bitmap = generateQRCodeBitmap(qrCodeContent, 400, 400)
  val uri = saveBitmapToCache(bitmap, context)

  val intent = Intent(Intent.ACTION_SEND).apply {
    type = "image/png"
    putExtra(Intent.EXTRA_STREAM, uri)
  }

  val shareIntent = Intent.createChooser(intent, "Share QR Code")
  startActivity(context, shareIntent, null)
}

private fun generateQRCodeBitmap(content: String, width: Int, height: Int): Bitmap
{
  val qrCodeWriter = QRCodeWriter()
  val bitMatrix = qrCodeWriter.encode(
    content,
    BarcodeFormat.QR_CODE,
    width,
    height,
    mapOf(
      Pair(com.google.zxing.EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.Q),
      Pair(com.google.zxing.EncodeHintType.CHARACTER_SET, "UTF-8")
    )
  )

  val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
  for (x in 0 until width) {
    for (y in 0 until height) {
      bitmap.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
    }
  }

  return bitmap
}

private fun saveBitmapToCache(bitmap: Bitmap, context: Context): Uri? {
  var uri: Uri? = null
  try {
    val cachePath = File(context.cacheDir, "images")
    cachePath.mkdirs()
    val stream = FileOutputStream("$cachePath/image.png")
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
    stream.close()
    val file = File("$cachePath/image.png")
    uri = FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
  } catch (e: IOException) {
    e.printStackTrace()
  }
  return uri
}
