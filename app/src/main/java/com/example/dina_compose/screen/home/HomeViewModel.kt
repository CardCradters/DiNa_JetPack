package com.example.dina_compose.screen.home


import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dina_compose.api.ApiConfig
import com.example.dina_compose.common.DataState
import com.example.dina_compose.common.safeApiCall
import com.example.dina_compose.data.UserRequest
import com.example.dina_compose.data.UserResponse
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(

) : ViewModel() {
  private val _users = MutableStateFlow<List<UserRequest>>(emptyList())
  val users: StateFlow<List<UserRequest>> = _users
  private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()


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
}
