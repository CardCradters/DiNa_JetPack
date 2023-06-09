package com.example.dina_compose.screen.profile

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dina_compose.api.ApiConfig
import com.example.dina_compose.common.DataState
import com.example.dina_compose.common.safeApiCall
import com.example.dina_compose.data.ProfileRequest
import com.example.dina_compose.data.UploadRequest
import com.example.dina_compose.data.UploadResponse
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class ProfileViewModel : ViewModel()
{
  private val _profile = MutableStateFlow<ProfileRequest?>(null)
  val profile: StateFlow<ProfileRequest?> = _profile
  private val _profilePicture = MutableStateFlow<Bitmap?>(null)
  val profilePicture: StateFlow<Bitmap?> = _profilePicture

//  private val _uploadMessage = MutableStateFlow<String?>(null)
//  val uploadMessage: StateFlow<String?> = _uploadMessage

  fun loadProfilePicture(context: Context, file: File, uploadRequest: UploadRequest) {
    val requestFile: RequestBody = RequestBody.create("image/*".toMediaTypeOrNull(), file)
    val filePart: MultipartBody.Part = MultipartBody.Part.createFormData("file", file.name, requestFile)

    val uid: RequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), uploadRequest.uid)
    val filename: RequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), uploadRequest.filename)
    val storagePath: RequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), uploadRequest.storagePath)

    viewModelScope.launch {
      when (val result = safeApiCall { ApiConfig.apiService(context).postUpload(filePart, uid, filename, storagePath) }) {
        DataState.Loading -> Unit
        is DataState.Error -> Log.e("Error", result.message)
        is DataState.Result -> {
          val responseBody = result.data

          // Handle the response based on its structure
          try {
            val uploadResponse = Gson().fromJson(responseBody.toString(), UploadResponse::class.java)
            val message = uploadResponse.message

            if (message == "File uploaded successfully") {
              val bitmap = convertFileToBitmap(file)
              _profilePicture.value = bitmap
            } else {
              Log.e("Error", "Failed to upload file: $message")
            }
          } catch (e: JsonSyntaxException) {
            Log.e("Error", "Failed to parse JSON response: $responseBody")
          }
        }
      }
    }
  }



  private fun convertFileToBitmap(file: File): Bitmap? {
    return try {
      BitmapFactory.decodeFile(file.path)
    } catch (e: Exception) {
      Log.e("Error", "Failed to convert file to bitmap: ${e.message}")
      null
    }
  }




  fun fetchProfile(context: Context) = viewModelScope.launch {
    when (val result = safeApiCall { ApiConfig.apiService(context).getProfile() })
    {
      DataState.Loading -> Unit
      is DataState.Error -> Log.e("salah", result.message)
      is DataState.Result ->
      {
        _profile.tryEmit(result.data.payloadx.datas)
      }
    }
  }

}
