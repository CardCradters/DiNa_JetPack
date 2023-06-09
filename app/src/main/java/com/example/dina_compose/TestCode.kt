package com.example.dina_compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dina_compose.component.DetailProfile
import com.example.dina_compose.ui.theme.DiNa_ComposeTheme
import com.example.dina_compose.ui.theme.verticalGradientBrush

@Composable
fun TestCode()
{
  val placeholders = listOf("Email", "Telephone", "FAX", "Mobile", "Website")
  val icons = listOf(
    painterResource(id = R.drawable.baseline_alternate_email_24),
    painterResource(id = R.drawable.baseline_phone_24),
    painterResource(id = R.drawable.baseline_fax_24),
    painterResource(id = R.drawable.baseline_phone_android_24),
    painterResource(id = R.drawable.baseline_public_24)
  )

  Column(
  ) {
//    Card(
//      modifier = Modifier
//        .padding(bottom = 8.dp),
//      shape = RoundedCornerShape(8.dp),
//      elevation = 5.dp,
//    ) {
//      Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center,
//      ) {
//        DetailProfile(times = 5, placeholderTexts = placeholders)
//      }
//    }

    Card(
      modifier = Modifier
        .padding(bottom = 8.dp),
      shape = RoundedCornerShape(8.dp),
      elevation = 5.dp,
    ) {
      Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 8.dp),
          horizontalArrangement = Arrangement.Center,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = "Company",
          )
        }
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 8.dp)
            .offset(y = (-16).dp),
          horizontalArrangement = Arrangement.Center,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = "Office Address",
          )
        }

        repeat(5) { index ->
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .height(56.dp)
              .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Icon(
              painter = icons[index],
              contentDescription = null
            )
            Text(
              text = "Office Address",
              modifier = Modifier.fillMaxWidth(),
              textAlign = TextAlign.End
            )
          }
          Divider(
            modifier = Modifier.fillMaxWidth(),
            color = Color.Black,
            thickness = 0.5.dp
          )
        }
        Divider(
          modifier = Modifier
            .fillMaxWidth()
            .height(72.dp),
          color = Color.Transparent
        )
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun TestCodeView()
{
  DiNa_ComposeTheme(darkTheme = false) { // A surface container using the
    // 'background' color from the theme
    Surface(
      modifier = Modifier
        .fillMaxSize()
        .background(brush = verticalGradientBrush),
      color = Color.Transparent,
    ) {
      TestCode()
    }
  }
}