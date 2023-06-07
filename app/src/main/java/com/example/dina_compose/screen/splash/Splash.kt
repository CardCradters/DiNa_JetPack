package com.example.dina_compose.screen.splash


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
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
      .padding(horizontal = 24.dp)
  ) {
    Text(
      text = "Welcome in DiNa",
      color = Color.Black,
      fontSize = 30.sp,
      fontWeight = FontWeight.Bold,
      modifier = Modifier
        .align(Alignment.Start)
        .padding(top = 38.dp)
    )
    Image(
      painter = painterResource(id = R.drawable.img_1),
      contentDescription = "Image",
      modifier = Modifier
        .fillMaxWidth()
        .height(508.dp)
    )
//    Button(
//      onClick = {
//        navController.navigate("login_screen")
//      },
//      colors = ButtonDefaults.buttonColors(Color(0xFF83B9E2)),
//      modifier = Modifier
//        .align(Alignment.CenterHorizontally)
//        .padding(top = 16.dp)
//    ) {
//    Text(text = "Let's Start")
//    }
  }
}
