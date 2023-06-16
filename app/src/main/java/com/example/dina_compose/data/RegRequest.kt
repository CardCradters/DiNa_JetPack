package com.example.dina_compose.data

import com.google.gson.annotations.SerializedName

data class RegRequest(

	@SerializedName("password")
	val password: String? = null,

	@SerializedName("phoneNumber")
	val phoneNumber: String? = null,

	@SerializedName("name")
	val name: String? = null,

	@SerializedName("email")
	val email: String? = null
)
