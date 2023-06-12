package com.example.dina_compose.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.dina_compose.R
import com.example.dina_compose.ui.theme.DiNa_ComposeTheme

@Composable
fun Barcode(onDismiss: () -> Unit)
{
  val context = LocalContext.current.applicationContext

  Dialog(
    onDismissRequest = {
      onDismiss() }
  ) {
    Surface(
      modifier = Modifier
        .fillMaxWidth(),
      color = MaterialTheme.colors.primary,
      shape = RoundedCornerShape(8.dp),
      elevation = 5.dp,
    ) {
      Column(
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Text(
          text = "SCAN ME !",
          style = MaterialTheme.typography.h2,
          modifier = Modifier
            .padding(vertical = 16.dp)
        )
        Box(
          modifier = Modifier
            .fillMaxWidth()
//            .height(150.dp)
            ,
          contentAlignment = Alignment.Center
        ) {
          Image(
            modifier = Modifier
              .padding(horizontal = 16.dp)
              .padding(bottom = 32.dp)
              .clip(shape = RoundedCornerShape(8.dp)),

            painter = painterResource(id = R.drawable.qr_code),
            contentDescription = "",
            alignment = Alignment.Center
          )
        }
      }
    }
  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BarcodePreview()
{
  DiNa_ComposeTheme(darkTheme = false) {
    Barcode{}
  }
}