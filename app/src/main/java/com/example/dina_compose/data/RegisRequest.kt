package com.example.dina_compose.data

import com.google.gson.annotations.SerializedName

data class RegisRequest(
  @SerializedName("name")
  val name: String,
  @SerializedName("phoneNumber")
  val phoneNumber: String,
  @SerializedName("email")
  val email: String,
  @SerializedName("password")
  val password: String,
  )