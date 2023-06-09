package com.example.dina_compose.screen.storage

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dina_compose.api.ApiConfig
import com.example.dina_compose.common.DataState
import com.example.dina_compose.common.safeApiCall
import com.example.dina_compose.data.UserRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StorageViewModel: ViewModel() {
  private val _allCard = MutableStateFlow<List<UserRequest>>(emptyList())
  val allCard: StateFlow<List<UserRequest>> = _allCard

  private val _searchCard = MutableStateFlow<List<UserRequest>>(emptyList())
  val searchCard: StateFlow<List<UserRequest>> = _searchCard

  private val _starCard = MutableStateFlow<List<UserRequest>>(emptyList())
  val starCard: StateFlow<List<UserRequest>> = _starCard

  private val _companyCard = MutableStateFlow<List<UserRequest>>(emptyList())
  val companyCard: StateFlow<List<UserRequest>> = _companyCard

  fun fetchAllCard(context: Context) = viewModelScope.launch {
    when (val result = safeApiCall { ApiConfig.apiService(context).getAllCard() }) {
      DataState.Loading -> Unit
      is DataState.Error -> Log.e("salah", result.message)
      is DataState.Result -> {
        //set data user from api
        _allCard.tryEmit(result.data.payload.datas)
      }
    }
  }

  fun fetchStarCard(context: Context) = viewModelScope.launch {
    when (val result = safeApiCall { ApiConfig.apiService(context).getStarCard() }) {
      DataState.Loading -> Unit
      is DataState.Error -> Log.e("salah", result.message)
      is DataState.Result -> {
        //set data user from api
        _starCard.tryEmit(result.data.payload.datas)
      }
    }
  }

  fun fetchCompanyCard(context: Context) = viewModelScope.launch {
    when (val result = safeApiCall { ApiConfig.apiService(context).getCompanyCard() }) {
      DataState.Loading -> Unit
      is DataState.Error -> Log.e("salah", result.message)
      is DataState.Result -> {
        //set data user from api
        _companyCard.tryEmit(result.data.payload.datas)
      }
    }
  }

  fun performSearch(context: Context, query: String) = viewModelScope.launch {
    when (val result = safeApiCall { ApiConfig.apiService(context).getSearchCard(query)}) {
      DataState.Loading -> Unit
      is DataState.Error -> Log.e("salah", result.message)
      is DataState.Result -> {
        _searchCard.tryEmit(result.data.payload.datas)
      }
    }
  }
}
