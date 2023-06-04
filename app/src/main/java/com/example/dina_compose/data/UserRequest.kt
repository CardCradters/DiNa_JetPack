package com.example.dina_compose.data

import com.google.gson.annotations.SerializedName

data class UserRequest(
  @SerializedName("uid")
  val uid: String,
  @SerializedName("name")
  val name: String,
  @SerializedName("job_title")
  val job_title: String,
  @SerializedName("workplace")
  val workplace: String,
  @SerializedName("stared")
  val stared: String,
  @SerializedName("filename")
  val filename: String,
  @SerializedName("storagePath")
  val storagePath: String,
  @SerializedName("users")
  val users: Collection<UserRequest>,
  )
