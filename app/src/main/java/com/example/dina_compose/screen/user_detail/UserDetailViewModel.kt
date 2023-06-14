package com.example.dina_compose.screen.user_detail

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dina_compose.api.ApiConfig
import com.example.dina_compose.common.DataState
import com.example.dina_compose.common.safeApiCall
import com.example.dina_compose.data.ProfileRequest
import com.example.dina_compose.data.StaredRequest
import com.example.dina_compose.data.UserRequest
import com.example.dina_compose.data.UserResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserDetailViewModel : ViewModel()
{
  private val _stared = MutableStateFlow<List<UserRequest>>(emptyList())
  val stared: StateFlow<List<UserRequest>> = _stared
  private val _userDetail = MutableStateFlow<ProfileRequest?>(null)
  val userDetail: StateFlow<ProfileRequest?> = _userDetail




  fun fetchUserDetail(context: Context, query: String) = viewModelScope.launch {
    when (val result =
      safeApiCall { ApiConfig.apiService(context).getUserDetail(query) })
    {
      DataState.Loading -> Unit
      is DataState.Error -> Log.e("salah", result.message)
      is DataState.Result ->
      {
        _userDetail.tryEmit(result.data.payloadx.datas)
      }
    }
  }

  suspend fun saveUser(
    id: String,
    request: StaredRequest,
    context: Context
  ): DataState<UserResponse>
  {
    return safeApiCall {
      ApiConfig.apiService(context).saveUser(id, request)
    }
  }

  suspend fun starred(
    id: String,
    request: StaredRequest,
    context: Context
  ): DataState<UserResponse>
  {
    return safeApiCall {
      ApiConfig.apiService(context).starred(id, request)
    }
  }
}


