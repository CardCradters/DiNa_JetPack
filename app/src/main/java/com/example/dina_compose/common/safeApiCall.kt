package com.example.dina_compose.common

import retrofit2.Response

suspend inline fun <reified T> safeApiCall(call: () -> Response<T>): DataState<T> {
    return try {
        val response = call.invoke()
        if (response.code() in 200..209) {
            val data = response.body()
            if (data != null) {
                DataState.Result(data)
            } else {
                DataState.Error("")
            }
        } else {
            DataState.Error("")
        }
    } catch (e: Exception) {
        DataState.Error(e.message.orEmpty())
    }
}