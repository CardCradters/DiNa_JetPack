package com.example.dina_compose.api

import com.example.dina_compose.data.RecognitionResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiServiceOcr
{
  @Multipart
  @POST("/ocr")
  suspend fun recognizeImage(
    @Part file: MultipartBody.Part
  ): Response<RecognitionResponse>
}

