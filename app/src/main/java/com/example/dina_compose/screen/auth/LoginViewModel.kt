package com.example.dina_compose.screen.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class LoginViewModel() : ViewModel() {
  private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()


  fun signInWithEmailAndPassword(
    email: String, password: String, callback: (Boolean, String) -> Unit
  ) = viewModelScope.launch {
    try {
      val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
      if (result.user != null) callback(true, "Berhasil Login")
      else callback(false, "Gagal Login")
    } catch (e: Exception) {
      callback(false, e.message.orEmpty())
    }
  }
}