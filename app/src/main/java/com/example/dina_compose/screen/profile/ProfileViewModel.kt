package com.example.dina_compose.screen.profile

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dina_compose.api.ApiConfig
import com.example.dina_compose.common.DataState
import com.example.dina_compose.common.safeApiCall
import com.example.dina_compose.data.PostProfileRequest
import com.example.dina_compose.data.PostProfileResponse
import com.example.dina_compose.data.ProfileRequest
import com.example.dina_compose.data.UploadRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class ProfileViewModel : ViewModel()
{
  private val _profile = MutableStateFlow<ProfileRequest?>(null)
  val profile: StateFlow<ProfileRequest?> = _profile
  private val _updateProfileResult = MutableLiveData<PostProfileResponse?>()
  val updateProfileResult: MutableLiveData<PostProfileResponse?> = _updateProfileResult
  private val _profilePicture = MutableStateFlow<String?>(null)
  val profilePicture: StateFlow<String?> = _profilePicture


  fun loadProfilePicture(context: Context, file: File, uploadRequest: UploadRequest) {
    val requestFile: RequestBody =
      RequestBody.create("image/*".toMediaTypeOrNull(), file)
    val filePart: MultipartBody.Part =
      MultipartBody.Part.createFormData("file", file.name, requestFile)
    val filename: RequestBody =
      RequestBody.create("text/plain".toMediaTypeOrNull(), uploadRequest.filename)

    viewModelScope.launch {
      when (val result =
        safeApiCall { ApiConfig.apiService(context).postUpload(filePart) }) {
        DataState.Loading -> Unit
        is DataState.Error -> Log.e("Error", result.message)
        is DataState.Result -> {
          val responseBody = result.data
          if (responseBody == "File successfully added") {
            val imageUrl = "https://example.com/images/${uploadRequest.filename}"
            _profilePicture.value = imageUrl
          } else {
            Log.e("Error", "Failed to upload file")
          }
        }
      }
    }
  }

  private fun convertFileToBitmap(file: File): Bitmap?
  {
    return try
    {
      BitmapFactory.decodeFile(file.path)
    } catch (e: Exception)
    {
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

  fun postProfile(
    context: Context,
    job_title:String,
    workplace: String,
    addressCompany: String,
    emailCompany: String,
    phoneTelpCompany: String,
    phoneFaxCompany: String,
    phoneMobileCompany: String,
    workplaceUri: String,
    callback: (Boolean, String) -> Unit = { _, _ -> }
  ) = viewModelScope.launch {
    val request = PostProfileRequest(
      jobTitle  = job_title,
      workplace = workplace,
      addressCompany = addressCompany,
      emailCompany = emailCompany,
      phoneTelpCompany = phoneTelpCompany,
      phoneFaxCompany = phoneFaxCompany,
      phoneMobileCompany = phoneMobileCompany,
      workplaceUri = workplaceUri,
    )

    when (val result = safeApiCall {
      ApiConfig.apiService(context).postProfile(request)
    })
    {
      is DataState.Error -> callback(false, result.message)
      DataState.Loading -> Unit
      is DataState.Result ->  _updateProfileResult.postValue(result.data)
    }
  }
}

private fun <T> MutableLiveData<T>.postValue(data: PostProfileResponse)
{
  this.postValue(data)
}
