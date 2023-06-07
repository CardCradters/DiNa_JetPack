package com.example.dina_compose.data

import com.google.gson.annotations.SerializedName

data class ProfileRequest(
  @SerializedName("uid")
  val uid: String,
  @SerializedName("name")
  val name: String,
  @SerializedName("password")
  val password: String,
  @SerializedName("job_title")
  val jobTitle: String,
  @SerializedName("workplace")
  val workplace: String,
  @SerializedName("workplace_uri")
  val workplaceUri: String,
  @SerializedName("addressCompany")
  val addressCompany: String,
  @SerializedName("emailCompany")
  val emailCompany: String,
  @SerializedName("email")
  val email: String,
  @SerializedName("phoneNumber")
  val phoneNumber: String,
  @SerializedName("phoneMobileCompany")
  val phoneMobileCompany: String,
  @SerializedName("phoneFaxCompany")
  val phoneFaxCompany: String,
  @SerializedName("phoneTelpCompany")
  val phoneTelpCompany: String,
  )