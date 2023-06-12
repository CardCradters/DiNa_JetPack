package com.example.dina_compose.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dina_compose.data.ProfileRequest

@Composable
fun CardProfile(profiles: ProfileRequest)
{
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
        text = profiles.name,
        style = MaterialTheme.typography.subtitle1,
      )
      Text(
        modifier = Modifier.padding(top = 8.dp),
        text = profiles.phoneNumber,
        style = MaterialTheme.typography.subtitle1,
      )
    }
  }

  Box(
    modifier = Modifier
      .padding(top = 24.dp)
  ) {
    Column(
      Modifier
        .fillMaxSize()
    ) {
      Text(
        profiles.workplace,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium
      )
    }
  }
}

