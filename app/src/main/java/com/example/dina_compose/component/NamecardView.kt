package com.example.dina_compose.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dina_compose.R
import com.example.dina_compose.ui.theme.DiNa_ComposeTheme

@Composable
fun NamecardView()
{
  Card(
    modifier = Modifier
      .padding(vertical = 16.dp)
      .aspectRatio(328f / 207f),
    shape = RoundedCornerShape(20.dp),
    elevation = 5.dp
  ) {
    Image(
      painter = painterResource(id = R.drawable.card_1),
      contentDescription = "Card Background",
      modifier = Modifier.fillMaxSize(),
      contentScale = ContentScale.Crop,
    )
    Text(
      text = "PT. AXIA PRIMA SEJAHTERA", //TODO ganti nama Company dari API
      style = MaterialTheme.typography.h1,
      modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp),
      color = Color.White,
    )
    Column(
      modifier = Modifier
        .padding(16.dp),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Text(
        text = "Username", //TODO ganti Username dari API
        style = MaterialTheme.typography.h2,
        color = Color.White,
        textDecoration = TextDecoration.Underline,
      )
      Text(
        text = "Job Title", //TODO ganti Job Titlle dari API
        style = MaterialTheme.typography.subtitle2,
        color = Color.White,
      )
    }
  }
}

@Preview
@Composable
fun NamecardPreview()
{
  DiNa_ComposeTheme(darkTheme = false) {
    NamecardView()
  }
}