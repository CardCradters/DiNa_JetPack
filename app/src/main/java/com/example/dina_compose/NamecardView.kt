package com.example.dina_compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NamecardView()
{
  Card(
    Modifier
      .padding(all = 16.dp)
      .aspectRatio(328f / 207f),
    shape = RoundedCornerShape(20.dp),
    elevation = 5.dp
  ) {
    Image(
      painter = painterResource(id = R.drawable.card_1),
      contentDescription = "Card Background",
      Modifier.fillMaxSize(),
      contentScale = ContentScale.Crop,
    )
    Column(
      Modifier
        .padding(16.dp),
    ) {
      Text(
        "LOGO", //TODO ganti nama Company dari API
        color = Color.White,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold
      )
      Text(
        "Logo Text", //TODO hapus aja kalo gak dipake
        color = Color.White,
        fontSize = 12.sp
      )
    }
    Column(
      Modifier
        .padding(16.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      Text(
        "Username", //TODO ganti Username dari API
        color = Color.White,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        textDecoration = TextDecoration.Underline,
        letterSpacing = 1.sp
      )
      Text(
        "Job Title", //TODO ganti Job Titlle dari API
        color = Color.White,
        fontSize = 12.sp
      )
    }
  }
}

@Preview(showBackground = true)
@Composable fun NamecardPreview()
{
  NamecardView()
}