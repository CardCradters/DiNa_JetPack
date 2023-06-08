package com.example.dina_compose.screen.bottomsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dina_compose.R

@Composable
fun About()
{
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(horizontal = 24.dp)
  ) {
    Spacer(Modifier.height(10.dp))
    Text(
      text = "About DiNa",
      color = Color.Black,
      fontSize = 30.sp,
      fontWeight = FontWeight.Bold,
      modifier = Modifier
        .align(Alignment.CenterHorizontally)
        .padding(top = 16.dp, bottom = 16.dp)
    )
    Spacer(Modifier.height(10.dp))
    Image(
      painter = painterResource(id = R.drawable.img_1),
      contentDescription = "Image",
      modifier = Modifier
        .align(Alignment.CenterHorizontally)
        .clip(shape = CircleShape)
        .size(200.dp)
        .border(width = 2.dp, color = Color.White, shape = CircleShape)
        .background(color = Color.Black.copy(alpha = ContentAlpha.medium))
        .shadow(elevation = 5.dp, shape = CircleShape)
    )
    Text(
      text = "About DiNa",
      color = Color.Black,
      fontSize = 15.sp,
      fontWeight = FontWeight.Bold,
      modifier = Modifier
        .align(Alignment.CenterHorizontally)
        .padding(top = 16.dp, bottom = 16.dp)
    )
  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun aboutPreview()
{
  About()
}