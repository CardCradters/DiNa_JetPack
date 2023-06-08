package com.example.dina_compose.screen.profile

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.example.dina_compose.api.ApiConfig
import com.example.dina_compose.common.DataState
import com.example.dina_compose.common.safeApiCall
import com.example.dina_compose.data.ProfileRequest
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
  private val _profilePicture = MutableLiveData<Bitmap?>()
  val profilePicture: MutableLiveData<Bitmap?> get() = _profilePicture

  fun loadProfilePicture(context: Context, profileRequest: ProfileRequest) =
    viewModelScope.launch {
    when (val result = safeApiCall { ApiConfig.apiService(context).postUpload() }) {
      DataState.Loading -> Unit
      is DataState.Error -> Log.e("salah", result.message)
      is DataState.Result -> {
        val profileData = result.data.payloadx.datas
        val profilePictureBitmap = getProfilePictureBitmap(profileRequest, context)
        _profilePicture.postValue(profilePictureBitmap)
      }
    }
  }

  private fun getProfilePictureBitmap(profileRequest: ProfileRequest, context: Context): Bitmap? {
    val fileName = profileRequest.filename
    return try {
      Glide.with(context)
        .asBitmap()
        .load(fileName)
        .submit()
        .get()
    } catch (e: Exception) {
      Log.e("salah", "Error loading profile picture: ${e.message}")
      null
    }
  }
}
