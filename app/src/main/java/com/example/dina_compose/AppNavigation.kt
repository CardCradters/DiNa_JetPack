package com.example.dina_compose

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dina_compose.api.ApiConfig
import com.example.dina_compose.screen.auth.Login
import com.example.dina_compose.screen.auth.Register
import com.example.dina_compose.screen.auth.RegisterViewModel
import com.example.dina_compose.screen.home.Home
import com.example.dina_compose.screen.splash.SplashScreen

@Composable
fun AppNavigation() {
  val navController = rememberNavController()

  NavHost(navController = navController, startDestination = "splash_screen") {
    composable("login_screen") {
      Login(navController = navController)
    }
    composable("register_screen") {
      Register(navController = navController)
    }
    composable("home_screen") {
      Home(navController = navController)
    }
    composable("splash_screen") {
      SplashScreen(navController = navController)
    }
  }

}