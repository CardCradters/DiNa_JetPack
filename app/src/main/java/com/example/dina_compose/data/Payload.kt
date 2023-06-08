package com.example.dina_compose.data

import com.google.gson.annotations.SerializedName

data class Payload(
  @SerializedName("datas")
  val datas: List<UserRequest>,
  @SerializedName("status_code")
  val statusCode: Int
)
