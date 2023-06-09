package com.example.dina_compose

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dina_compose.data.ProfileRequest
import com.example.dina_compose.screen.auth.Login
import com.example.dina_compose.screen.auth.Register
import com.example.dina_compose.screen.bottomsheet.About
import com.example.dina_compose.screen.home.Home
import com.example.dina_compose.screen.profile.Profile
import com.example.dina_compose.screen.splash.SplashScreen
import com.example.dina_compose.screen.storage.Storage

@Composable
fun AppNavigation()
{
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
    composable("about") {
      About()
    }
    composable("profile_screen") {
      Profile(
        navController = navController
      )
    }
    composable("storage_screen") {
      Storage(
        navController = navController
      )
    }
  }
}

