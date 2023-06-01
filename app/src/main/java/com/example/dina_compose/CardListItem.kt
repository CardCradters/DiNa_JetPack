package com.example.dina_compose

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dina_compose.ui.theme.DiNa_ComposeTheme

@Composable
fun CardListItem(contextForToast: Context)
{
  Card(
    Modifier
      .clickable {
        Toast
          .makeText(contextForToast, "Card List Clicked", Toast.LENGTH_SHORT)
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
          painter = painterResource(id = R.drawable.user),
          contentDescription = "Profile Picture",
          Modifier
            .clip(shape = CircleShape)
            .size(60.dp)
            .background(color = Color.Black)
        )
        Column(
          Modifier
            .padding(start = 8.dp),
          verticalArrangement = Arrangement.Center
        ) {
          Text(
            text = "Username",
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(bottom = 4.dp)
          )
          Text(
            text = "Job Title",
            style = MaterialTheme.typography.subtitle2,
          )
          Text(
            text = "Company Name",
            style = MaterialTheme.typography.subtitle2
          )
        }
      }
      Icon(
        painter = painterResource(id = R.drawable.baseline_star_outline_24),
        contentDescription = "Star"
      )
    }
  }
}

@Preview()
@Composable
fun CardListItemPreview()
{
  DiNa_ComposeTheme(darkTheme = false) {
    CardListItem(contextForToast = LocalContext.current.applicationContext)
  }
}