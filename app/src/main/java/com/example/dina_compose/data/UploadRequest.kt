package com.example.dina_compose.data

import com.google.gson.annotations.SerializedName



data class UploadRequest(
  val uid: String,
//  val name: String,
//  val job_title: String,
//  val email: String,
//  val workplace: String,
//  val stared: String,
  val filename: String,
  val storagePath: String
)
