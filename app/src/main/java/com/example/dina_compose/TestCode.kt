package com.example.dina_compose

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dina_compose.component.DetailProfile
import com.example.dina_compose.ui.theme.DiNa_ComposeTheme
import com.example.dina_compose.ui.theme.verticalGradientBrush

@Composable
fun ClickableIcon(
  isStared: Boolean,
  onClick: () -> Unit
)
{
  Icon(
    painter = painterResource(
      id = if (isStared) R.drawable.baseline_star_outline_24 else R.drawable
        .baseline_star_25
    ),
    contentDescription = "Star",
    modifier = Modifier.clickable { onClick() }
  )
}

@Composable
fun CardListItemTest(
//  user: UserRequest,
//  context: Context,
//  viewModel: UserDetailViewModel,
//  navController: NavController
)
{
//  val painter = run {
//    if (!user.filename.isNullOrEmpty()) {
//      rememberImagePainter(data = user.filename)
//    } else {
//      painterResource(id = R.drawable.baseline_account_circle_24)
//    }
//  }
//  val userDetail by viewModel.userDetail.collectAsState()
  Card(
    Modifier
//      .clickable {
//        navController.navigate("detail_screen/${user.uid}/${user.name}") {
//          launchSingleTop = true
//          popUpTo(navController.graph.findStartDestination().id) {
//            saveState = true
//          }
//        }?:   logMessage("User detail not available")
//      }
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
//          painter = painter,
          painter = painterResource(id = R.drawable.user),
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
            .padding(start = 8.dp)
            .weight(1f),
//              .fillMaxHeight(),
          verticalArrangement = Arrangement.Center
        ) {
          Text(
//            user.name,
            text = "Andi Muhammad Satria Fadhil Panjangin Lagi",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 1.sp,
            modifier = Modifier.padding(bottom = 4.dp)
          )
          Text(
//            user.job_title,
            text = "Backend Developer",
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal
          )
          Text(
//            user.workplace,
            text = "Google",
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal
          )
        }
        ClickableIcon(
//        isStared = user.stared
          isStared = true
        ) {
//        viewModel.starred(
//          context = context,
//          uid = user.uid,
//          name = user.name,
//          job_title = user.job_title,
//          workplace = user.workplace,
//          isStarred = !user.stared,
//          filename = user.filename ?: "",
//          storagePath = user.storagePath,
//        )
        }
      }
    }
  }
}

fun logMessage(message: String)
{
  Log.d("MyApp", message)
}

@Preview()
@Composable
fun CardListItemTestPreview()
{
  DiNa_ComposeTheme(darkTheme = false) {
    CardListItemTest()
  }
}