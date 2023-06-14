package com.example.dina_compose.screen.auth

import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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
      fontWeight = FontWeight.Bold,
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

    Spacer(Modifier.height(24.dp))

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

    var passwordVisible by remember { mutableStateOf(false) }
    TextField(
      value = passwordValue,
      onValueChange = { passwordValue = it },
      shape = RoundedCornerShape(8.dp),
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 16.dp),
      label = { Text(text = stringResource(R.string.enter_password)) },
      singleLine = true,
      visualTransformation = if (passwordVisible) {
        VisualTransformation.None
      } else {
        PasswordVisualTransformation()
      },
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
      colors = TextFieldDefaults.textFieldColors(
        textColor = Color.Gray,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent
      ),
      trailingIcon = {
        val visibilityIcon = if (passwordVisible) {
          Icons.Filled.VisibilityOff
        } else {
          Icons.Filled.Visibility
        }
        IconButton(
          onClick = { passwordVisible = !passwordVisible }
        ) {
          Icon(
            imageVector = visibilityIcon,
            contentDescription = null
          )
        }
      }
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
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
          }
        }
      },
      colors = buttonColors(Color(0xFF83B9E2)),
      modifier = Modifier
        .fillMaxWidth()
        .height(44.dp)
//          .padding(top = 16.dp)
    ) {
      Text(
        text = stringResource(R.string.register),
        style = androidx.compose.material.MaterialTheme.typography.subtitle1,
        fontWeight = FontWeight.Bold,
        color = Color.Black
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
    Spacer(Modifier.height(4.dp))
    Row(
      modifier = Modifier
        .align(Alignment.CenterHorizontally),
//          .padding(top = 16.dp),
    ) {
      Text(
        text = stringResource(R.string.have_namecard),
        style = MaterialTheme.typography.bodySmall,
        color = Color.Black
      )
      Spacer(Modifier.width(4.dp))
      ClickableText(
        text = AnnotatedString(stringResource(R.string.open_scanner)),
        style = MaterialTheme.typography.labelMedium,
        onClick = {
          navController.navigate("register2_screen")
        }
      )
    }
  }

}