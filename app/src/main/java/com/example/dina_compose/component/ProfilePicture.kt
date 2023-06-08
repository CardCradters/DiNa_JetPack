package com.example.dina_compose.component

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.dina_compose.R

@Composable
fun ProfilePicture()
{
  val context = LocalContext.current.applicationContext
  Box(
    modifier = Modifier
      .offset(y = 50.dp),
    contentAlignment = Alignment.BottomEnd,
  ) {
    Image(
      painter = painterResource(id = R.drawable.baseline_account_circle_24),
      contentDescription = "Profile Picture",
      modifier = Modifier
        .size(140.dp)
        .clip(shape = CircleShape)
        .border(width = 2.dp, color = Color.White, shape = CircleShape)
        .shadow(elevation = 5.dp)
        .background(color = Color.Gray)
    )
    IconButton(
      onClick = {
        Toast.makeText(context, "Click!", Toast.LENGTH_SHORT).show()
      },
      enabled = false
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

//@Preview(showBackground = true)
//@Composable
//fun ProfileView()
//{
//  DiNa_ComposeTheme(darkTheme = false) { // A surface container using the
//    // 'background' color from the theme
//    Surface(
//      modifier = Modifier
//        .fillMaxSize()
//        .background(brush = verticalGradientBrush),
//      color = Color.Transparent,
//    ) {
//      ProfilePicture()
//    }
//  }
//}
