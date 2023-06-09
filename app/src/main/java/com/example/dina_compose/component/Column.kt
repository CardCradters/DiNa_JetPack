package com.example.dina_compose.component

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.dina_compose.R
import com.example.dina_compose.data.ProfileRequest
import com.example.dina_compose.data.UploadRequest
import com.example.dina_compose.screen.profile.ProfileViewModel
import com.example.dina_compose.ui.theme.DiNa_ComposeTheme
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

@Composable
fun ProfilePicture(viewModel: ProfileViewModel)
{
  val profilePicture = viewModel.profilePicture
  val context = LocalContext.current.applicationContext
  val galleryLauncher = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.GetContent()
  ) { uri: Uri? ->
    uri?.let { selectedUri ->
      val inputStream = context.contentResolver.openInputStream(selectedUri)
      val file = File(context.cacheDir, "temp_image.jpg")
      file.outputStream().use { outputStream ->
        inputStream?.copyTo(outputStream)
      }

      // Buat objek RequestBody dengan tipe "image/*" dan file yang telah disalin ke cache
      val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())

      // Buat part dengan nama "file" sesuai dengan kebutuhan API
      val imagePart = MultipartBody.Part.createFormData("file", file.name, requestFile)

      val uploadRequest = UploadRequest(
        uid = "123",
        filename = "profile_picture.jpg",
        storagePath = "/uploads/profiles"
      )
      // Kirim part ke ViewModel untuk mengunggah gambar ke backend
      viewModel.loadProfilePicture(context, file, uploadRequest)
    }
  }


  Box(
    modifier = Modifier
      .offset(y = 50.dp),
    contentAlignment = Alignment.BottomEnd,
  ) {

    Image(
      painter = rememberImagePainter(profilePicture),
      contentDescription = "Profile Picture",
      modifier = Modifier
        .size(140.dp)
        .clip(shape = CircleShape)
        .border(width = 2.dp, color = Color.White, shape = CircleShape)
        .background(color = Color.Black.copy(alpha = ContentAlpha.medium))
        .shadow(elevation = 5.dp, shape = CircleShape)
    )


    IconButton(
      onClick = {
        galleryLauncher.launch("image/*")
      }
    ) {
      Icon(
        modifier = Modifier
          .clip(shape = CircleShape)
          .background(color = Color.Black)
          .border(width = 1.dp, color = Color.White, shape = CircleShape)
          .padding(4.dp),
        imageVector = Icons.Outlined.Edit,
        tint = Color.White,
        contentDescription = "Edit"
      )
    }

  }
}



