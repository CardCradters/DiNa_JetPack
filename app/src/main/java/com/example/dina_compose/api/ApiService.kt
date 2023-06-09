package com.example.dina_compose.api

import com.example.dina_compose.data.ProfileResponse
import com.example.dina_compose.data.RegisRequest
import com.example.dina_compose.data.RegisResponse
import com.example.dina_compose.data.UploadRequest
import com.example.dina_compose.data.UploadResponse
import com.example.dina_compose.data.UserRequest
import com.example.dina_compose.data.UserResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService
{
  @GET("v1/homepage")
  suspend fun getUsers(): Response<UserResponse>

  @GET("v1/homepage/{id}")
  suspend fun search(@Path("id") id: String): Response<UserResponse>

  @GET("v1/profile")
  suspend fun getProfile(): Response<ProfileResponse>

  @GET("v1/cardstorage/all")
  suspend fun getAllCard(): Response<UserResponse>

  @GET("v1/cardstorage")
  suspend fun getCompanyCard(): Response<UserResponse>

  @GET("v1/cardstorage/star")
  suspend fun getStarCard(): Response<UserResponse>

  @GET("v1/cardstorage/{id}")
  suspend fun getSearchCard(@Path("id") id: String): Response<UserResponse>


  @Multipart
  @POST("v1/upload")
  suspend fun postUpload(
    @Part file: MultipartBody.Part,
    @Part("uid") uid: RequestBody,
    @Part("filename") filename: RequestBody,
    @Part("storagePath") storagePath: RequestBody
  ): Response<UploadResponse>


  @POST("v1/auth/signup")
  suspend fun registerUser(
    @Body registerRequest: RegisRequest
  ): Response<RegisResponse>

  @POST("v1/cardstorage/star/{id}")
  suspend fun starred(
    @Path("id") id: String,
    @Body userRequest: UserRequest
  ): Response<UserResponse>
}



