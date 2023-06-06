package com.example.dina_compose.ui.theme
import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColors(
  primary = Purple40, secondary = PurpleGrey80,
)
private val LightColorScheme = lightColors(
  primary = Purple40, secondary = PurpleGrey40, background = LightBlue
  /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun DiNa_ComposeTheme(
  darkTheme: Boolean = isSystemInDarkTheme(), // Dynamic color is available on Android 12+
  dynamicColor: Boolean = true, content: @Composable () -> Unit
)
{
  val colorScheme = when
  {
    dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S ->
    {
      if (darkTheme) DarkColorScheme else LightColorScheme
    }

    darkTheme -> DarkColorScheme
    else -> LightColorScheme
  }
  val view = LocalView.current
  if (!view.isInEditMode)
  {
    SideEffect {
      val window = (view.context as Activity).window
      window.statusBarColor = colorScheme.background.toArgb()
      WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
        darkTheme
    }
  }

  MaterialTheme(
    colors = colorScheme, typography = MyTypography, content = content
  )
}