package com.example.dina_compose.screen.auth


import android.net.Uri
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.dina_compose.R
import com.example.dina_compose.api.ApiConfigOcr
import com.example.dina_compose.common.UiState
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.File
import java.io.FileOutputStream

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Register2(
  navController: NavController,
  viewModel: RegisterViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
  var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
  val context = LocalContext.current
  var registrationSuccessful by remember { mutableStateOf(false) }
  val scrollState = rememberLazyListState()
  var passwordValue by remember { mutableStateOf("") }
  var nameValue by remember { mutableStateOf("") }
  var emailValue by remember { mutableStateOf("") }
  var phoneValue by remember { mutableStateOf("") }
  val ocrViewModel = remember { OcrViewModel(apiConfigOcr = ApiConfigOcr()) }

  val pickImageLauncher =
    rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
      uri?.let {
        selectedImageUri = uri

        val imageFile = File(
          context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
          "business_card.jpg"
        )
        val outputStream = FileOutputStream(imageFile)
        val inputStream = context.contentResolver.openInputStream(selectedImageUri!!)
        inputStream?.use { input ->
          outputStream.use { output ->
            input.copyTo(output)
          }
        }

        val requestBody = RequestBody.create("image/jpeg".toMediaTypeOrNull(), imageFile)
        val filePart =
          MultipartBody.Part.createFormData("file", imageFile.name, requestBody)

        ocrViewModel.scanImageForText(
          filePart,
          { recognitionResponse ->
            // Handle successful OCR response
            nameValue = recognitionResponse.name?.get(0) ?: ""
            emailValue = recognitionResponse.email!!
            phoneValue = recognitionResponse.phoneNumber!!
          }
        ) { exception ->
          // Handle failure
          Log.e("OCR", "Error during OCR: ${exception.message}")
        }
      }
    }

  // TODO

  val updateRegisState by rememberUpdatedState(newValue = viewModel.updateRegis.value)
  val buttonState = remember {
    mutableStateOf(true)
  }

  LaunchedEffect(updateRegisState) {
    when (val currentState = updateRegisState) {

      is UiState.Loading -> {
        buttonState.value = false
      }

      is UiState.Success -> {
        buttonState.value = true
        Toast.makeText(context, currentState.data, Toast.LENGTH_SHORT).show()
        navController.navigate("login_screen")
      }

      is UiState.Error -> {
        buttonState.value = true
        Toast.makeText(context, currentState.errorMessage, Toast.LENGTH_LONG).show()
      }

      else -> {
        buttonState.value = true
        // Nothing
      }
    }
  }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(horizontal = 24.dp)
  ) {
    androidx.compose.material3.Text(
      text = "Sign Up",
      fontSize = 20.sp,
      fontWeight = FontWeight.Bold,
      modifier = Modifier
        .padding(top = 16.dp)
        .align(Alignment.Start)
    )
    androidx.compose.material3.Text(
      text = stringResource(R.string.desc_regis),
      style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
      modifier = Modifier
        .padding(top = 8.dp)
        .align(Alignment.Start)
    )
    Spacer(Modifier.height(24.dp))

    if (selectedImageUri != null) {
      Image(
        painter = rememberImagePainter(selectedImageUri),
        contentDescription = "Scan Media Input",
        contentScale = ContentScale.Fit,
        modifier = Modifier
          .fillMaxWidth()
          .size(200.dp)
          .padding(20.dp)
          .clip(RoundedCornerShape(8.dp))
      )
    } else {
      Button(
        onClick = {
          // Launch the image picker
          pickImageLauncher.launch("image/*")
        },
        modifier = Modifier
          .fillMaxWidth()
          .size(200.dp)
          .padding(20.dp)
          .clip(RoundedCornerShape(8.dp))
      ) {
        Text(text = "Select Image")
      }
    }

    androidx.compose.material3.TextField(
      value = nameValue,
      onValueChange = { nameValue = it },
      shape = RoundedCornerShape(8.dp),
      modifier = Modifier
        .fillMaxWidth(),
      label = { androidx.compose.material3.Text(text = stringResource(R.string.enter_Name)) },
      singleLine = true,
      colors = TextFieldDefaults.textFieldColors(
//        textColor = Color.Gray,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent
      )
    )
    androidx.compose.material3.TextField(
      value = emailValue,
      onValueChange = { emailValue = it },
      shape = RoundedCornerShape(8.dp),
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 16.dp),
      label = { androidx.compose.material3.Text(text = stringResource(R.string.email)) },
      singleLine = true,
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
      colors = TextFieldDefaults.textFieldColors(
//        textColor = Color.Gray,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent
      )
    )
    androidx.compose.material3.TextField(
      value = phoneValue,
      onValueChange = { phoneValue = it },
      shape = RoundedCornerShape(8.dp),
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 16.dp),
      label = { androidx.compose.material3.Text(text = stringResource(R.string.enter_phone)) },
      singleLine = true,
      colors = TextFieldDefaults.textFieldColors(
//        textColor = Color.Gray,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent
      )
    )


    var passwordVisible by remember { mutableStateOf(false) }
    androidx.compose.material3.TextField(
      value = passwordValue,
      onValueChange = { passwordValue = it },
      shape = RoundedCornerShape(8.dp),
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 16.dp),
      label = { androidx.compose.material3.Text(text = stringResource(R.string.enter_password)) },
      singleLine = true,
      visualTransformation = if (passwordVisible) {
        VisualTransformation.None
      } else {
        PasswordVisualTransformation()
      },
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
      colors = TextFieldDefaults.textFieldColors(
//        textColor = Color.Gray,
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
    androidx.compose.material3.Button(
      enabled = buttonState.value, // TODO
      onClick = {
        viewModel.registerUser(
          name = nameValue,
          email = emailValue,
          phoneNumber = phoneValue,
          password = passwordValue,
          context = context
        )
//        { success, message ->
//          Log.e("Register", "Registration error: eror")
//          registrationSuccessful = success
//          if (success)
//          {
//            navController.navigate("login_screen")
//          } else
//          {
//            Log.e("Registration", "Error: $message")
//          }
//        }
      },
      colors = ButtonDefaults.buttonColors(Color(0xFF83B9E2)),
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 16.dp)
    ) {
      // TODO
      if (buttonState.value) {
        androidx.compose.material3.Text(text = stringResource(R.string.register))
      } else {
        CircularProgressIndicator()
      }

    }
  }
}

