package com.example.dina_compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.dina_compose.ui.theme.DiNa_ComposeTheme

@Composable
fun HomeScreen()
{
//  var searchText by remember { mutableStateOf("") }
  Column(
    modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    SearchBar()
    NamecardView()
    Text(
      text = "Digitize Your Network",
      style = MaterialTheme.typography.body1,
      fontSize = 24.sp,
    )
    CardList()
  }
}

@Preview()
@Composable
fun HomeScreenPreview()
{
  DiNa_ComposeTheme(darkTheme = false) {
    HomeScreen()
  }
}
