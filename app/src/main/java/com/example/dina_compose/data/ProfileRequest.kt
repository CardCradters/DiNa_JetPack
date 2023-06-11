package com.example.dina_compose.data

import com.google.gson.annotations.SerializedName

data class ProfileRequest(
  @SerializedName("uid")
  val uid: String,
  @SerializedName("name")
  val name: String,
  @SerializedName("phoneNumber")
  val phoneNumber: String,
  @SerializedName("job_title")
  val job_title: String,
  @SerializedName("workplace")
  val workplace: String,
  @SerializedName("password")
  val password: String,
  @SerializedName("workplaceUri")
  val workplaceUri: String,
  @SerializedName("addressCompany")
  val addressCompany: String,
  @SerializedName("emailCompany")
  val emailCompany: String,
  @SerializedName("email")
  val email: String,
  @SerializedName("phoneMobileCompany")
  val phoneMobileCompany: String,
  @SerializedName("phoneFaxCompany")
  val phoneFaxCompany: String,
  @SerializedName("phoneTelpCompany")
  val phoneTelpCompany: String,
  @SerializedName("filename")
  val filename: String,
  @SerializedName("storagePath")
  val storagePath: String,
  )
//
//{
//  // Implementasi lain dari kelas ProfileRequest
//
//  fun getDatas(): ProfileRequest {
//    return this
//  }
//}
