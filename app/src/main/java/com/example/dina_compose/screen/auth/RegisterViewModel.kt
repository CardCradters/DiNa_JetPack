package com.example.dina_compose.screen.auth

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dina_compose.api.ApiConfig
import com.example.dina_compose.common.DataState
import com.example.dina_compose.common.safeApiCall
import com.example.dina_compose.data.RegisRequest
import kotlinx.coroutines.launch

class RegisterViewModel() : ViewModel() {

  fun registerUser(
    name: String,
    phone: String,
    email: String,
    password: String,
    context: Context,
    callback: (Boolean, String) -> Unit = { _, _ -> }
  ) = viewModelScope.launch {
    when (val result = safeApiCall {
      ApiConfig.apiService(context).registerUser(
        RegisRequest(
          name = name,
          phoneNumber = phone,
          email = email,
          password = password
        )
      )
    }) {
      is DataState.Error -> callback(false, result.message)
      DataState.Loading -> Unit
      is DataState.Result -> callback(true, "Berhasil")
    }
  }



}




