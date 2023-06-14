package com.example.dina_compose.screen.auth

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import com.example.dina_compose.data.RecognitionResponse
import com.google.mlkit.vision.common.InputImage
import okhttp3.*
import java.io.IOException

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Register2(
  navController: NavController,
  viewModel: RegisterViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
  respons: RecognitionResponse
)
{
  var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
  val context = LocalContext.current
//  var nameValue by remember { mutableStateOf("") }
//  var emailValue by remember { mutableStateOf("") }
//  var phoneValue by remember { mutableStateOf("") }
  var passwordValue by remember { mutableStateOf("") }
  var scannedNameValue by remember { mutableStateOf("") }
  var scannedEmailValue by remember { mutableStateOf("") }
  var scannedPhoneValue by remember { mutableStateOf("") }
  val ocrViewModel = remember { OcrViewModel(apiConfigOcr = ApiConfigOcr()) }

  val pickImageLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
    // Process the selected image URI here
    selectedImageUri = uri
    uri?.let {
      createInputImageFromUri(context, it)?.let { inputImage ->
        ocrViewModel.scanImageForText(
          inputImage,
          { text ->
            scannedNameValue = extractNameFromText(text.toString())
            scannedEmailValue = extractEmailFromText(text.toString())
            scannedPhoneValue = extractPhoneNumberFromText(text.toString())
          },
          { exception ->
            // Handle failure
            Log.e("OCR", "Error during OCR: ${exception.message}")
          }
        )
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
        contentDescription = null,
        modifier = Modifier
          .fillMaxWidth()
          .aspectRatio(1f)
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
          .aspectRatio(1f)
          .clip(RoundedCornerShape(8.dp))
      ) {
        Text(text = "Select Image")
      }
    }

    androidx.compose.material3.TextField(
      value = scannedNameValue,
      onValueChange = { scannedNameValue = it },
      shape = RoundedCornerShape(8.dp),
      modifier = Modifier
        .fillMaxWidth(),
      label = { androidx.compose.material3.Text(text = stringResource(R.string.enter_Name)) },
      singleLine = true,
      colors = TextFieldDefaults.textFieldColors(
        textColor = Color.Gray,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent
      )
    )

    androidx.compose.material3.TextField(
      value = scannedEmailValue,
      onValueChange = { scannedEmailValue = it },
      shape = RoundedCornerShape(8.dp),
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 16.dp),
      label = { androidx.compose.material3.Text(text = stringResource(R.string.enter_phone)) },
      singleLine = true,
      colors = TextFieldDefaults.textFieldColors(
        textColor = Color.Gray,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent
      )
    )

    androidx.compose.material3.TextField(
      value = scannedPhoneValue,
      onValueChange = { scannedPhoneValue = it },
      shape = RoundedCornerShape(8.dp),
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 16.dp),
      label = { androidx.compose.material3.Text(text = stringResource(R.string.email)) },
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
    androidx.compose.material3.TextField(
      value = passwordValue,
      onValueChange = { passwordValue = it },
      shape = RoundedCornerShape(8.dp),
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 16.dp),
      label = { androidx.compose.material3.Text(text = stringResource(R.string.enter_password)) },
      singleLine = true,
      visualTransformation = if (passwordVisible)
      {
        VisualTransformation.None
      } else
      {
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
        val visibilityIcon = if (passwordVisible)
        {
          Icons.Filled.VisibilityOff
        } else
        {
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
    androidx.compose.material3.Button(
      onClick = {
        viewModel.registerUser(
          name = scannedNameValue,
          email = scannedEmailValue,
          phone = scannedPhoneValue,
          password = passwordValue,
          context = context
        ) { success, message ->
          Toast.makeText(context, message, Toast.LENGTH_LONG).show()
          if (success)
          {
            navController.navigate("login_screen")
          } else
          {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
          }
        }
      },
      colors = ButtonDefaults.buttonColors(Color(0xFF83B9E2)),
      modifier = Modifier
        .fillMaxWidth()
        .height(44.dp)
//          .padding(top = 16.dp)
    ) {
      androidx.compose.material3.Text(
        text = stringResource(R.string.register),
        style = MaterialTheme.typography.subtitle1,
        fontWeight = FontWeight.Bold,
        color = Color.Black
      )
    }
  }

}
fun createInputImageFromUri(context: Context, uri: Uri): InputImage? {
  try {
    val contentResolver: ContentResolver = context.contentResolver

    // Decode the bitmap from the Uri
    val bitmap: Bitmap? = if (Build.VERSION.SDK_INT < 28) {
      // For API level below 28
      @Suppress("DEPRECATION")
      MediaStore.Images.Media.getBitmap(contentResolver, uri)
    } else {
      // For API level 28 and above
      val source = ImageDecoder.createSource(contentResolver, uri)
      ImageDecoder.decodeBitmap(source)
    }

    // Create InputImage from the bitmap
    val image = bitmap?.let {
      InputImage.fromBitmap(it, 0)
    }

    return image
  } catch (e: IOException) {
    e.printStackTrace()
  }
  return null
}
//private fun createBitmapFromUri(context: Context, uri: Uri): Bitmap? {
//  return try {
//    val inputStream = context.contentResolver.openInputStream(uri)
//    val bitmap = BitmapFactory.decodeStream(inputStream)
//    inputStream?.close()
//    bitmap
//  } catch (e: IOException) {
//    e.printStackTrace()
//    null
//  }
//}



// Fungsi untuk ekstraksi nama dari RecognitionResponse
private fun extractNameFromText(text: String): String
{
  val nameRegex = Regex("\\b\\p{Lu}\\p{Ll}+\\b")
  val matches = nameRegex.findAll(text.toString())
  val names = matches.map { it.value }
  return names.firstOrNull() ?: ""
}

// Fungsi untuk ekstraksi email dari RecognitionResponse
private fun extractEmailFromText(text: String): String
{
  val emailRegex = Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")
  val matches = emailRegex.findAll(text.toString())
  val emails = matches.map { it.value }
  return emails.joinToString(", ")
}

// Fungsi untuk ekstraksi nomor telepon dari RecognitionResponse
private fun extractPhoneNumberFromText(text: String): String
{
  val phoneRegex = Regex("\\b\\d{3}[\\s.-]?\\d{3}[\\s.-]?\\d{4}\\b")
  val matches = phoneRegex.findAll(text.toString())
  val phoneNumbers = matches.map { it.value }
  return phoneNumbers.joinToString(", ")
}

