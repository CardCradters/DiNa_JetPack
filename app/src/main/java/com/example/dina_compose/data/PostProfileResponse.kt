package com.example.dina_compose.data

import com.google.gson.annotations.SerializedName

data class PostProfileResponse(

	@field:SerializedName("payloadz")
	val payloadz: Payloadz? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class Datas(

	@field:SerializedName("_writeTime")
	val writeTime: WriteTime? = null
)

data class WriteTime(

	@field:SerializedName("_nanoseconds")
	val nanoseconds: Int? = null,

	@field:SerializedName("_seconds")
	val seconds: Int? = null
)

data class Payloadz(

	@field:SerializedName("status_code")
	val statusCode: Int? = null,

	@field:SerializedName("datas")
	val datas: Datas? = null
)
