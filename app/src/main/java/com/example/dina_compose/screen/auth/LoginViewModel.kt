package com.example.dina_compose.screen.auth

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dina_compose.common.UiState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class LoginViewModel() : ViewModel() {
  private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
  private val _updateLogin: MutableState<UiState<String>> = mutableStateOf(UiState.Empty)
  val updateRegis: State<UiState<String>>
    get() = _updateLogin


  fun signInWithEmailAndPassword(
    email: String, password: String, callback: (Boolean, String) -> Unit
  ) = viewModelScope.launch {
    _updateLogin.value = UiState.Loading
    try {
      val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
      if (result.user != null) callback(true, "Berhasil Login")
      else callback(false, "Gagal Login")
    } catch (e: Exception) {
      callback(false, e.message.orEmpty())
    }
  }
}