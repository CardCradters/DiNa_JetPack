package com.example.dina_compose.screen.share
import android.graphics.Bitmap
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.journeyapps.barcodescanner.BarcodeEncoder

class ShareViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
  var qrCode: String = ""
    set(value) {
      savedStateHandle.set("QR CODE", value)
      field = value
    }

  init {
    savedStateHandle.get<String>("QR CODE")?.run {
      qrCode = this
    }
  }

  // Function to generate the QR code bitmap
  fun generateQRCodeBitmap(): Bitmap? {
    if (qrCode.isBlank()) {
      return null
    }

    val barcodeEncoder = BarcodeEncoder()
    return barcodeEncoder.encodeBitmap(
      qrCode,
      com.google.zxing.BarcodeFormat.QR_CODE,
      400,
      400
    )
  }
}
