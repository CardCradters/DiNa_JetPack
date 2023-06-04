package com.example.dina_compose.screen.auth

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dina_compose.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(navController: NavController, viewModel: LoginViewModel = androidx.lifecycle.viewmodel.compose.viewModel())
{
  var emailValue by remember { mutableStateOf("") }
  var passwordValue by remember { mutableStateOf("") }
  val context = LocalContext.current


  Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
    Box(
      modifier = Modifier
        .fillMaxSize()
        .background(
          Brush.linearGradient(
            listOf(Color(0xFF83B9E2), Color(0xFFFFFFFF))
          )
        )
    )
  }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(horizontal = 24.dp)
  ) {
    Text(
      text = "Sign In",
      color = Color.Black,
      fontSize = 20.sp,
      fontWeight = FontWeight.Bold,
      modifier = Modifier
        .align(Alignment.Start)
        .padding(top = 8.dp)
    )
    Text(
      text = stringResource(R.string.welcome),
      color = Color.Black,
      modifier = Modifier
        .align(Alignment.Start)
        .padding(top = 16.dp, bottom = 8.dp)
    )
    Image(
      painter = painterResource(R.drawable.img_3),
      contentDescription = "Profile Picture",
      modifier = Modifier
        .size(350.dp)
        .padding(top = 10.dp)
//                .align(Alignment.CenterHorizontally)
    )

    TextField(
      value = emailValue,
      onValueChange = { emailValue = it },
      shape = RoundedCornerShape(7.dp),
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 8.dp),
      label = { Text(text = stringResource(id = R.string.email)) },
      singleLine = true,
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
      colors = TextFieldDefaults.textFieldColors(
        textColor = Color.Gray,
      )
    )

    TextField(
      value = passwordValue,
      onValueChange = { passwordValue = it },
      shape = RoundedCornerShape(7.dp),
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 8.dp),
      label = { Text(text = stringResource(R.string.enter_password)) },
      singleLine = true,
      visualTransformation = PasswordVisualTransformation(),
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
      colors = TextFieldDefaults.textFieldColors(
        textColor = Color.Gray
      )
    )
    Spacer(Modifier.height(10.dp))
    Button(
      onClick = {
        viewModel.signInWithEmailAndPassword(emailValue, passwordValue) { success, message ->
          if (success)
          {
            navController.navigate("home_screen")
          } else
          {
            Toast.makeText(context, message, Toast.LENGTH_SHORT)
          }
        }
      },
      colors = ButtonDefaults.buttonColors(Color(0xFF83B9E2)),
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 16.dp)
    ) {
      Text(text = stringResource(R.string.login))
    }



    Row(
      modifier = Modifier
        .align(Alignment.CenterHorizontally)
        .padding(top = 16.dp),
    ) {
      Text(text = stringResource(R.string.or_sigin), fontSize = 10.sp, color = Color.Black)
    }
    Spacer(Modifier.height(10.dp))
    IconButton(
      onClick = { /*TODO*/ },
      modifier = Modifier
        .size(width = 124.dp, height = 48.dp)
        .padding(top = 16.dp)
        .align(Alignment.CenterHorizontally)
    ) {
      Image(
        painter = painterResource(R.drawable.img_5),
        contentDescription = "Google",
        modifier = Modifier.size(width = 90.dp, height = 90.dp)
      )
    }
    Spacer(Modifier.height(16.dp))
    Row(
      modifier = Modifier
        .align(Alignment.CenterHorizontally)
        .padding(top = 16.dp),
    ) {
      Text(
        text = stringResource(R.string.have_account), fontSize = 10.sp,
        color = Color.Black,
//
      )
      Spacer(Modifier.width(3.dp))
      ClickableText(
        text = AnnotatedString(stringResource(R.string.register)),
        onClick = {
          navController.navigate("register_screen")
        }, style = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Normal)
      )
    }
  }
}

@Preview
@Composable
fun LoginPreview()
{
  val navController = rememberNavController()

  MaterialTheme {
    Login(
      navController = navController
    )
  }
}
