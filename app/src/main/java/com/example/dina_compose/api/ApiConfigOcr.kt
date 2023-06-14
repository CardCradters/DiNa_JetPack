package com.example.dina_compose.api

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfigOcr {
  private val baseUrl: String = "https://perform-ocr-wf2dwnbrwq-et.a.run.app/"

  fun getApiService(): ApiServiceOcr {
    val loggingInterceptor = HttpLoggingInterceptor().apply {
      level = HttpLoggingInterceptor.Level.BODY
    }

    val client = OkHttpClient.Builder()
      .addInterceptor(loggingInterceptor)
      .build()

    val retrofit = Retrofit.Builder()
      .baseUrl(baseUrl)
      .client(client)
      .addConverterFactory(GsonConverterFactory.create())
      .build()

    return retrofit.create(ApiServiceOcr::class.java)
  }

  fun createRequestBody(text: String): MultipartBody.Part {
    val requestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), text)
    return MultipartBody.Part.createFormData("file", null, requestBody)
  }


}




