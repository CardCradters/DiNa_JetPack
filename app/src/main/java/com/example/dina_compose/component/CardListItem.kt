package com.example.dina_compose.component

import android.content.Context
import android.util.Log
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
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.dina_compose.R
import com.example.dina_compose.data.UserRequest
import com.example.dina_compose.screen.user_detail.UserDetailViewModel

@Composable
fun ClickableIcon(
  isStared: Boolean,
  onClick: () -> Unit
) {
  Icon(
    painter = painterResource(id = if (isStared) R.drawable.baseline_star_25 else R.drawable
      .baseline_star_outline_24),
    contentDescription = "Star",
    modifier = Modifier.clickable { onClick() }
  )
}


@Composable
fun CardListItem(
  user: UserRequest, context: Context, viewModel: UserDetailViewModel,
  navController: NavController
)
{
//  val painter = run {
//    if (!user.filename.isNullOrEmpty())
//    {
//      rememberImagePainter(data = user.filename)
//    } else
//    {
//      painterResource(id = R.drawable.baseline_account_circle_24)
//    }
//  }
  val userDetail by viewModel.userDetail.collectAsState()



  Card(
    Modifier
      .clickable {
        navController.navigate("detail_screen/${user.uid}/${user.name}") {
          launchSingleTop = true
          popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
          }
        } ?: logMessage("User detail not available")
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
          painter =  painterResource(id = R.drawable.baseline_account_circle_24),
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
      ClickableIcon(isStared = user.stared) {
        if (user.stared) {
          viewModel.deleteStar(context, user.uid)
        } else {
          viewModel.starred(context, user.uid)
        }
      }
    }
  }
}


fun logMessage(message: String)
{
  Log.d("MyApp", message)
}