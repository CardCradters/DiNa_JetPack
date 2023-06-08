package com.example.dina_compose.data

import com.google.gson.annotations.SerializedName

data class UserResponse(
  @SerializedName("message")
  val message: String,
  @SerializedName("payload")
  val payload: Payload
)

