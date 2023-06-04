package com.example.dina_compose.api

import com.example.dina_compose.data.RegisRequest
import com.example.dina_compose.data.RegisResponse
import com.example.dina_compose.data.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService
{
  @GET("v1/homepage")
  suspend fun getUsers(): Response<UserResponse>

  @GET("v1/homepage/:id")
  suspend fun search(): Response<UserResponse>

  @POST("v1/auth/signup")
  suspend fun registerUser(
    @Body registerRequest: RegisRequest
  ): Response<RegisResponse>

}



