package com.example.dina_compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen()
{
  var searchText by remember { mutableStateOf("") }
  Column(
    modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    SearchBar()
    NamecardView()
    Text("Digitize Your Network",
      fontSize = 24.sp,
      fontWeight = FontWeight.SemiBold)
    CardList()
  }
}

@Preview(
  showBackground = true,
  showSystemUi = true
)
@Composable
fun PreviewHomeScreen()
{
  HomeScreen()
}
