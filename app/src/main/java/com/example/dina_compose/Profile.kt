package com.example.dina_compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dina_compose.ui.theme.DiNa_ComposeTheme

@Composable
fun Profile()
{
//  var searchText by remember { mutableStateOf("") }
  Column(
    modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    NamecardView()
    TestCode()
    Column(modifier = Modifier
      .fillMaxWidth()
      .height(72.dp)
      .background(color = Color.Black)) {
    }
  }
}

@Preview
@Composable
fun ProfilePreview()
{
  DiNa_ComposeTheme(darkTheme = false) {
    Profile()
  }
}