package com.example.dina_compose.data

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

	@field:SerializedName("payloady")
	val payload: Payloady? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class Payloady(

	@field:SerializedName("status_code")
	val statusCode: Int? = null,

	@field:SerializedName("datasx")
	val datas: Datasx? = null
)

data class Datasx(

	@field:SerializedName("uid")
	val uid: String? = null,

	@field:SerializedName("emailVerified")
	val emailVerified: Boolean? = null,

	@field:SerializedName("metadata")
	val metadata: Metadata? = null,

	@field:SerializedName("phoneNumber")
	val phoneNumber: String? = null,

	@field:SerializedName("providerData")
	val providerData: List<ProviderDataItem?>? = null,

	@field:SerializedName("disabled")
	val disabled: Boolean? = null,

	@field:SerializedName("tokensValidAfterTime")
	val tokensValidAfterTime: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)

data class ProviderDataItem(

	@field:SerializedName("uid")
	val uid: String? = null,

	@field:SerializedName("providerId")
	val providerId: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("phoneNumber")
	val phoneNumber: String? = null
)

data class Metadata(

	@field:SerializedName("lastSignInTime")
	val lastSignInTime: Any? = null,

	@field:SerializedName("creationTime")
	val creationTime: String? = null,

	@field:SerializedName("lastRefreshTime")
	val lastRefreshTime: Any? = null
)
