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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dina_compose.R
import com.example.dina_compose.data.ProfileRequest
import com.example.dina_compose.screen.profile.ProfileViewModel
import com.example.dina_compose.ui.theme.DiNa_ComposeTheme

@Composable
fun NamecardView(viewModel: ProfileViewModel)
{
  val contextForToast = LocalContext.current
  val profile: ProfileRequest? by viewModel.profile.collectAsState(null)
  LaunchedEffect(Unit) {
    viewModel.fetchProfile(contextForToast)
  }
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
    val Company = if (profile?.workplace.isNullOrEmpty()) {
      "Company"
    } else {
      profile?.workplace
    }
    Company?.let {
    Text(
      text = Company,
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
      val name = if (profile?.name.isNullOrEmpty()) {
        "Username"
      } else {
        profile?.name
      }
      name?.let {
      Text(
        text = name,
        style = MaterialTheme.typography.h2,
        color = Color.White,
        textDecoration = TextDecoration.Underline,
      )
        val job = if (profile?.job_title.isNullOrEmpty()) {
          "Job Title"
        } else {
          profile?.job_title
        }
        job?.let {
      Text(
        text = job,
        style = MaterialTheme.typography.subtitle2,
        color = Color.White
      )

    }
  }
}}}}

//@Preview
//@Composable
//fun NamecardPreview()
//{
//  DiNa_ComposeTheme(darkTheme = false) {
//    NamecardView()
//  }
//}