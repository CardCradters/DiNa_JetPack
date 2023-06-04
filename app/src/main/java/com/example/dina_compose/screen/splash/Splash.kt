package com.example.dina_compose.screen.splash

import android.window.SplashScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController

@Composable
fun SplashScreen(
    navController: NavHostController,
    viewModel:SplashViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    LaunchedEffect(key1 = viewModel, block = {
        viewModel.checkIsLoggedIn {
            navController.navigate(
                if(it) "home_screen" else "login_screen"
            )
        }
    })
}