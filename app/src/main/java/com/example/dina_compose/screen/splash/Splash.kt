package com.example.dina_compose.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.dina_compose.R

@Composable
fun SplashScreen(
  navController: NavHostController,
  viewModel: SplashViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
)
{
  LaunchedEffect(key1 = viewModel, block = {
    viewModel.checkIsLoggedIn {
      navController.navigate(
        if (it) "home_screen" else "login_screen"
      )
    }
  })

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(horizontal = 24.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Text(
      text = "Welcome in DiNa",
      color = Color.Black,
      fontSize = 30.sp,
      fontWeight = FontWeight.Bold,
      modifier = Modifier
        .padding(top = 38.dp)
    )
    Image(
      painter = painterResource(id = R.drawable.img_1),
      contentDescription = "Image",
      modifier = Modifier
        .fillMaxWidth()
        .aspectRatio(3f / 4f)
    )
    Button(
      onClick = {
        navController.navigate("login_screen")
      },
      colors = ButtonDefaults.buttonColors(MaterialTheme.colors.primary),
      modifier = Modifier
        .padding(top = 16.dp)
        .width(240.dp)
        .height(44.dp),
    ) {
      Text(
        text = "Let's Start",
        style = MaterialTheme.typography.subtitle1,
        fontWeight = FontWeight.Bold,
        color = Color.Black
      )
    }
  }
}

//@Preview(showBackground = true)
//@Composable
//fun SplashView()
//{
//  val navController = rememberNavController()
//
//  DiNa_ComposeTheme(darkTheme = false) { // A surface container using the
//    // 'background' color from the theme
//    Surface(
//      modifier = Modifier
//        .fillMaxSize()
//        .background(brush = verticalGradientBrush),
//      color = Color.Transparent,
//    ) {
//      SplashScreen(navController = navController)
//    }
//  }
//}