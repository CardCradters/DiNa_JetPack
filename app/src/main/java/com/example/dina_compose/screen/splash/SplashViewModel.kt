package com.example.dina_compose.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class SplashViewModel() : ViewModel() {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun checkIsLoggedIn(
        callback: (Boolean) -> Unit = {}
    ) = viewModelScope.launch {
        callback(firebaseAuth.currentUser != null)

    }
}
