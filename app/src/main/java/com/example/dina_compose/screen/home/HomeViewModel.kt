package com.example.dina_compose.screen.home


import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dina_compose.api.ApiConfig
import com.example.dina_compose.common.DataState
import com.example.dina_compose.common.safeApiCall
import com.example.dina_compose.data.ProfileRequest
import com.example.dina_compose.data.UserRequest
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class HomeViewModel(
) : ViewModel() {
  private val _users = MutableStateFlow<List<UserRequest>>(emptyList())
  val users: StateFlow<List<UserRequest>> = _users
  private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

  private val _searchResults = MutableStateFlow<List<UserRequest>>(emptyList())
  val searchResult: StateFlow<List<UserRequest>> = _searchResults

  private val _stared = MutableStateFlow<List<UserRequest>>(emptyList())
  val stared : StateFlow<List<UserRequest>>  = _stared

  fun fetchUsers(context: Context) = viewModelScope.launch {
    when (val result = safeApiCall { ApiConfig.apiService(context).getUsers() }) {
      DataState.Loading -> Unit
      is DataState.Error -> Log.e("salah", result.message)
      is DataState.Result -> {
        //set data user from api
        _users.tryEmit(result.data.payload.datas)
      }
    }
  }

  fun signOut(callback:()->Unit={})=viewModelScope.launch {
    firebaseAuth.signOut()
    callback()
  }

  fun performSearch(context: Context, query: String) = viewModelScope.launch {
    when (val result = safeApiCall { ApiConfig.apiService(context).search(query)}) {
      DataState.Loading -> Unit
      is DataState.Error -> Log.e("salah", result.message)
      is DataState.Result -> {
        _searchResults.tryEmit(result.data.payload.datas)
      }
    }
  }

  fun starred(context: Context,
              uid: String,
              name: String,
              job_title: String,
              workplace: String,
              isStarred: Boolean,
              filename: String,
              storagePath: String,
              password: String,
              workplaceUri: String,
              addressCompany: String,
              emailCompany: String,
              email: String,
              phoneMobileCompany: String,
              phoneFaxCompany: String,
              phoneTelpCompany: String,
              phoneNumber: String,
              callback: (Boolean, String) -> Unit = { _, _ -> }
  ) = viewModelScope.launch {

    val userRequest = UserRequest(
      uid = uid,
      name = name,
      job_title = job_title,
      workplace = workplace,
      stared = isStarred,
      filename = filename,
      storagePath = storagePath,
      password =password,
      workplaceUri = workplaceUri,
      addressCompany =addressCompany,
      emailCompany =emailCompany,
      email =email,
      phoneMobileCompany =phoneMobileCompany,
      phoneFaxCompany =phoneFaxCompany,
      phoneTelpCompany =phoneTelpCompany,
      phoneNumber =phoneNumber,
    )

    when (val result = safeApiCall { ApiConfig.apiService(context).starred(uid, userRequest)
    }) {

      is DataState.Error -> callback(false, result.message)
      DataState.Loading -> Unit
      is DataState.Result -> callback(true, "Berhasil")
        }
      }
    }
