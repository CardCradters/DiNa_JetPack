package com.example.dina_compose.api

import com.example.dina_compose.data.ProfileRequest
import com.example.dina_compose.data.ProfileResponse
import com.example.dina_compose.data.RegisRequest
import com.example.dina_compose.data.RegisResponse
import com.example.dina_compose.data.UserRequest
import com.example.dina_compose.data.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService
{
  @GET("v1/homepage")
  suspend fun getUsers(): Response<UserResponse>

  @GET("v1/homepage/{id}")
  suspend fun search(@Path("id") id: String): Response<UserResponse>

  @GET("v1/profile")
  suspend fun getProfile(): Response<ProfileResponse>

  @POST("v1/upload")
  suspend fun postUpload(): Response<ProfileResponse>

  @POST("v1/auth/signup")
  suspend fun registerUser(
    @Body registerRequest: RegisRequest
  ): Response<RegisResponse>

  @POST("v1/cardstorage/star/{id}")
  suspend fun starred(
    @Path("id") id: String,
    @Body profileRequest: ProfileRequest
  ): Response<ProfileResponse>
}



