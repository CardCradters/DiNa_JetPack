package com.example.dina_compose.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dina_compose.ui.theme.DiNa_ComposeTheme
import com.example.dina_compose.ui.theme.verticalGradientBrush

@Composable
fun TestComp()
{
  val placeholders = listOf("Email", "Telephone", "FAX", "Mobile", "Website")
  val scrollState = rememberLazyListState()

  Column(
    modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Box(
      modifier = Modifier,
      contentAlignment = BottomCenter
    ) {
      NamecardView()
      ProfilePicture()
    }
    Card(
      modifier = Modifier
        .padding(top = 66.dp)
        .fillMaxWidth(),
      shape = RoundedCornerShape(8.dp),
      elevation = 5.dp,
    ) {
      Column(
        modifier = Modifier
          .padding(all = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
      ) {
        Text(
          text = "Username",
          style = MaterialTheme.typography.subtitle1,
        )
        Text(
          modifier = Modifier.padding(top = 8.dp),
          text = "+62 89923234819",
          style = MaterialTheme.typography.subtitle1,
        )
      }
    }

    Column(
      Modifier
        .padding(top = 24.dp)
        .fillMaxSize()
    ) {
      Text(
        "Company",
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium
      )
      Spacer(Modifier.height(16.dp))

      LazyColumn(
        modifier = Modifier
          .fillMaxSize(),
        state = scrollState
      ) {
        item {
          Card(
            shape = RoundedCornerShape(8.dp),
            elevation = 5.dp,
          ) {
            Column(
              horizontalAlignment = Alignment.CenterHorizontally,
              verticalArrangement = Arrangement.Center,
            ) {
              DetailProfile(times = 5, placeholderTexts = placeholders)
            }
          }
        }
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun TestCompView()
{
  DiNa_ComposeTheme(darkTheme = false) { // A surface container using the
    // 'background' color from the theme
    Surface(
      modifier = Modifier
        .fillMaxSize()
        .background(brush = verticalGradientBrush),
      color = Color.Transparent,
    ) {
      TestComp()
    }
  }
}