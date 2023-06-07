package com.example.dina_compose


import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.dina_compose.ui.theme.DiNa_ComposeTheme
import com.example.dina_compose.ui.theme.verticalGradientBrush

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    setContent {
      DiNa_ComposeTheme(darkTheme = false,) { // A surface container using the
        // 'background' color from the theme
        Surface(
          modifier = Modifier
            .fillMaxSize()
            .background(brush = verticalGradientBrush),
          color = Color.Transparent,
        ) {
          AppNavigation()
        }
      }
    }
  }
}
