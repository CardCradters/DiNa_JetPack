package com.example.dina_compose.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dina_compose.data.UserRequest
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel
  (

) : ViewModel() {
  private val _users = MutableStateFlow<List<UserRequest>>(emptyList())
  val users: StateFlow<List<UserRequest>> = _users
  private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()


//  fun fetchUsers(context: Context) = viewModelScope.launch {
//    when (val result = safeApiCall { ApiConfig.apiService(context).getUsers() }) {
//      DataState.Loading -> Unit
//      is DataState.Error -> Log.e("salah", result.message)
//      is DataState.Result -> {
//        //set data user from api
//        _users.tryEmit(result.data.payload.datas)
//      }
//    }
  }

//  fun signOut(callback:()->Unit={})=viewModelScope.launch {
//    firebaseAuth.signOut()
//    callback()
//  }
//}