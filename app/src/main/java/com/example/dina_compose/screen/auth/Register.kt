package com.example.dina_compose.screen.auth

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dina_compose.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Register(
  navController: NavController,
  viewModel: RegisterViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
)
{
  var nameValue by remember { mutableStateOf("") }
  var emailValue by remember { mutableStateOf("") }
  var phoneValue by remember { mutableStateOf("") }
  var passwordValue by remember { mutableStateOf("") }
  var registrationSuccessful by remember { mutableStateOf(false) }
  val context = LocalContext.current


  Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
    Box(
      modifier = Modifier
        .fillMaxSize()
        .background(
          Brush.verticalGradient(
            colors = listOf(Color(0xFF83B9E2), Color(0xFFFFFFFF)),
            startY = 0f,
            endY = Float.POSITIVE_INFINITY,
            tileMode = TileMode.Clamp,
          )
        )
    )
  }

    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 24.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      if (registrationSuccessful)
      {
        Text(
          text = "Registrasi Berhasil!",
          color = Color.Green,
          modifier = Modifier.padding(bottom = 16.dp)
        )
      }
      Text(
        text = "Sign Up",
        fontSize = 20.sp,
        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
        modifier = Modifier
          .padding(top = 16.dp)
          .align(Alignment.Start)
      )
      Text(
        text = stringResource(R.string.desc_regis),
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier
          .padding(top = 8.dp)
          .align(Alignment.Start)
      )
      Image(
        painter = painterResource(id = R.drawable.edi),
        contentDescription = "Profile Picture",
        modifier = Modifier
          .padding(vertical = 16.dp)
          .size(120.dp)
          .clip(shape = CircleShape)
          .border(width = 2.dp, color = Color.White, shape = CircleShape)
          .shadow(elevation = 5.dp, shape = CircleShape)
//          .align(Alignment.CenterHorizontally)
      )

      TextField(
        value = nameValue,
        onValueChange = { nameValue = it },
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
          .fillMaxWidth(),
        label = { Text(text = stringResource(R.string.enter_Name)) },
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
          textColor = Color.Gray,
          focusedIndicatorColor = Color.Transparent,
          unfocusedIndicatorColor = Color.Transparent,
          disabledIndicatorColor = Color.Transparent
        )
      )

      TextField(
        value = phoneValue,
        onValueChange = { phoneValue = it },
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 16.dp),
        label = { Text(text = stringResource(R.string.enter_phone)) },
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
          textColor = Color.Gray,
          focusedIndicatorColor = Color.Transparent,
          unfocusedIndicatorColor = Color.Transparent,
          disabledIndicatorColor = Color.Transparent
        )
      )

      TextField(
        value = emailValue,
        onValueChange = { emailValue = it },
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 16.dp),
        label = { Text(text = stringResource(R.string.email)) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.textFieldColors(
          textColor = Color.Gray,
          focusedIndicatorColor = Color.Transparent,
          unfocusedIndicatorColor = Color.Transparent,
          disabledIndicatorColor = Color.Transparent
        )
      )

      TextField(
        value = passwordValue,
        onValueChange = { passwordValue = it },
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 16.dp),
        label = { Text(text = stringResource(R.string.enter_password)) },
        singleLine = true,
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.textFieldColors(
          textColor = Color.Gray,
          focusedIndicatorColor = Color.Transparent,
          unfocusedIndicatorColor = Color.Transparent,
          disabledIndicatorColor = Color.Transparent
        )
      )
      Spacer(Modifier.height(24.dp))
      Button(
        onClick = {
          viewModel.registerUser(
            name = nameValue,
            email = emailValue,
            phone = phoneValue,
            password = passwordValue,
            context = context
          ) { success, message ->
            registrationSuccessful = success
            if (success)
            {
              navController.navigate("login_screen")
            } else
            {
              Toast.makeText(context, message, Toast.LENGTH_LONG)
            }
          }
        },
        colors = buttonColors(Color(0xFF83B9E2)),
        modifier = Modifier
          .fillMaxWidth()
//          .padding(top = 16.dp)
      ) {
        Text(
          text = stringResource(R.string.register),
          style = MaterialTheme.typography.titleMedium,
          fontWeight = FontWeight.Bold
        )
      }


      Row(
        modifier = Modifier
          .align(Alignment.CenterHorizontally)
          .padding(top = 16.dp),
      ) {
        Text(
          text = stringResource(R.string.or_sigup),
          style = MaterialTheme.typography.bodySmall,
          color = Color.Black
        )
      }
      Spacer(Modifier.height(24.dp))
      IconButton(
        onClick = { /*TODO*/ },
        modifier = Modifier
//        .size(width = 124.dp, height = 48.dp)
//        .padding(top = 16.dp)
          .fillMaxWidth()
          .align(Alignment.CenterHorizontally)
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically
        ) {
          Image(
            painter = painterResource(R.drawable.google),
            contentDescription = "Google",
            modifier = Modifier.size(32.dp)
          )
          Text(
            text = "Google",
            modifier = Modifier.padding(start = 8.dp),
            style = MaterialTheme.typography.titleSmall,
          )
        }
      }
      Spacer(Modifier.height(24.dp))
      Row(
        modifier = Modifier
          .align(Alignment.CenterHorizontally),
//          .padding(top = 16.dp),
      ) {
        Text(
          text = stringResource(R.string.have_account),
          style = MaterialTheme.typography.bodySmall,
          color = Color.Black
        )
        Spacer(Modifier.width(4.dp))
        ClickableText(
          text = AnnotatedString(stringResource(R.string.login)),
          style = MaterialTheme.typography.labelMedium,
          onClick = {
            navController.navigate("login_screen")
          }
        )
      }
    }

}

@Preview(showBackground = true)
@Composable
fun RegisView()
{

}
