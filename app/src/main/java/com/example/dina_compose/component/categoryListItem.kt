package com.example.dina_compose.component

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.dina_compose.R
import com.example.dina_compose.data.UserRequest
import com.example.dina_compose.screen.storage.StorageViewModel


@Composable
fun categoryListItem(user: UserRequest, context: Context, viewModel:
StorageViewModel, navController : NavController)
{
  Card(
    Modifier
      .clickable {
        navController.navigate("detail_screen/${user.uid}") {
          // Kirim data pengguna sebagai argumen
          launchSingleTop = true
          restoreState = true
        }
        Toast
          .makeText(context, "Card List Clicked", Toast.LENGTH_SHORT)
          .show()
      }
      .wrapContentHeight()
      .fillMaxWidth(),
    elevation = 3.dp,
    shape = RoundedCornerShape(8.dp)
  ) {
    Row(
      Modifier
        .wrapContentHeight()
        .padding(horizontal = 16.dp)
        .padding(vertical = 16.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Image(
          painter = if (!user.filename.isNotEmpty()) {
            rememberImagePainter(data = user.filename)
          } else {
            painterResource(id = R.drawable.baseline_account_circle_24)
          },
          contentDescription = "Profile Picture",
          modifier = Modifier
            .clip(shape = CircleShape)
            .size(60.dp)
            .background(color = MaterialTheme.colors.primary)
            .border(
              width = 2.dp,
              color = MaterialTheme.colors.secondary,
              shape = CircleShape
            )
        )

        Column(
          Modifier
            .padding(start = 8.dp),
//              .fillMaxHeight(),
          verticalArrangement = Arrangement.Center
        ) {
          Text(
            user.name,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 1.sp,
            modifier = Modifier.padding(bottom = 4.dp)
          )
          Text(
            user.job_title,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal
          )
          Text(
            user.workplace,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal
          )
        }
      }
//      ClickableIcon(
//        isStared = user.stared
//      ) {
//        viewModel.starred(context, user.uid, !user.stared)
//      }
    }
  }

}
