package com.example.dina_compose.data

import com.google.gson.annotations.SerializedName

data class PostProfileRequest
  (
  @SerializedName("phoneMobileCompany")
  val phoneMobileCompany: String? = null,

  @SerializedName("phoneTelpCompany")
  val phoneTelpCompany: String? = null,

  @SerializedName("workplace_uri")
  val workplaceUri: String? = null,

  @SerializedName("phoneFaxCompany")
  val phoneFaxCompany: String? = null,

  @SerializedName("addressCompany")
  val addressCompany: String? = null,

  @SerializedName("emailCompany")
  val emailCompany: String? = null,

  @SerializedName("job_title")
  val jobTitle: String? = null,

  @SerializedName("workplace")
  val workplace: String? = null
      )

