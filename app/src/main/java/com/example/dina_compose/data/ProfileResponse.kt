package com.example.dina_compose.data

import com.google.gson.annotations.SerializedName

data class ProfileResponse(

	@SerializedName("payload")
	val payloadx: PayloadX,

	@SerializedName("message")
	val message: String
)

data class PayloadX(

	@SerializedName("status_code")
	val statusCode: Int,

	@SerializedName("datas")
	val datas: ProfileRequest
)

