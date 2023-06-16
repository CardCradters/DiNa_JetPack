package com.example.dina_compose.data

data class RecognitionResponse(
  val phoneNumber: String? = null,
  val name: List<String?>? = null,
  val email: String? = null
)