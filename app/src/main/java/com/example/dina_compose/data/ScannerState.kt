package com.example.dina_compose.data

data class ScannerState(

var openScan: Boolean = false,
var code: String = ""
)

sealed class ScannerEvents {
  object CloseScanner : ScannerEvents()
  object OpenScanner : ScannerEvents()
  data class SetCode(var code: String) : ScannerEvents()
}

