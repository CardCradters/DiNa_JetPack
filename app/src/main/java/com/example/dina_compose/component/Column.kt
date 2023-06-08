package com.example.dina_compose.component

import android.content.Context
import android.graphics.Bitmap
import android.widget.Toast
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dina_compose.R
import com.example.dina_compose.screen.profile.ProfileViewModel
import com.example.dina_compose.ui.theme.DiNa_ComposeTheme


@Composable
fun ProfilePicture(context: Context,viewModel: ProfileViewModel )
{
  val profilePicture: Bitmap? by viewModel.profilePicture.observeAsState(null)
  val context = LocalContext.current.applicationContext
  Box(
    modifier = Modifier
      .offset(y = 50.dp),
    contentAlignment = Alignment.BottomEnd,
  ) {
    Image(
      painter = painterResource(id = R.drawable.edi),
      contentDescription = "Profile Picture",
      Modifier
        .size(140.dp)
        .clip(shape = CircleShape)
        .border(width = 2.dp, color = Color.White, shape = CircleShape)
        .background(color = Color.Black.copy(alpha = ContentAlpha.medium))
        .shadow(elevation = 5.dp, shape = CircleShape)
    )
    IconButton(
      onClick = {
        Toast.makeText(context, "Click!", Toast.LENGTH_SHORT).show()
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



@Preview(showBackground = true)
@Composable
fun TestCodePreview()
{
  DiNa_ComposeTheme(darkTheme = false) {
//    column()
  }
}