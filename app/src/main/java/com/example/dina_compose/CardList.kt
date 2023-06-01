package com.example.dina_compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dina_compose.ui.theme.DiNa_ComposeTheme

@Composable
fun CardList()
{
  val contextForToast = LocalContext.current.applicationContext
  val scrollState = rememberScrollState()
  Box {
    Column(
      Modifier
        .padding(all = 16.dp)
        .fillMaxSize()
    ) {
      Text(
        "Last Activity",
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium
      )
      Spacer(Modifier.height(16.dp))
      Column(
        Modifier.verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(
          space = 8.dp
        )
      ) {
        repeat(10) {
          CardListItem(contextForToast = contextForToast)
        }
      }
    }
  }
}

@Preview()
@Composable
fun CardListPreview()
{
  DiNa_ComposeTheme(darkTheme = false) {
    CardList()
  }
}