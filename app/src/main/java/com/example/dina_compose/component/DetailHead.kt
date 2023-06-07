package com.example.dina_compose.component

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun DetailHead(placeholder: String = "Enter Your Name")
{
  var value by remember { mutableStateOf("") }
  val context = LocalContext.current.applicationContext
  val focusManager = LocalFocusManager.current

  Column(
    Modifier
      .fillMaxSize()
      .clip(shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
      .background(color = Color.White.copy(alpha = ContentAlpha.high)),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    BasicTextField(
      value = value,
      onValueChange = { newText ->
        value = newText
      },
      textStyle = TextStyle(
        textAlign = TextAlign.Center
      ),
      singleLine = true,
      decorationBox = { innerTextField ->
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp), // inner padding
          contentAlignment = Alignment.Center
        ) {
          if (value.isEmpty())
          {
            Text(
              text = placeholder,
            )
          }
          innerTextField()
        }
      },
      keyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Done
      ),
      keyboardActions = KeyboardActions(
        onDone = {
          focusManager.clearFocus()

          Toast.makeText(
            context,
            "On Done Click: value = $value",
            Toast.LENGTH_SHORT
          )
            .show()
        }
      ),
    )
    BasicTextField(
      value = value,
      onValueChange = { newText ->
        value = newText
      },
      textStyle = TextStyle(
        textAlign = TextAlign.Center
      ),
      singleLine = true,
      decorationBox = { innerTextField ->
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp), // inner padding
          contentAlignment = Alignment.Center
        ) {
          if (value.isEmpty())
          {
            Text(
              text = placeholder,
            )
          }
          innerTextField()
        }
      },
      keyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Done
      ),
      keyboardActions = KeyboardActions(
        onDone = {
          focusManager.clearFocus()

          Toast.makeText(
            context,
            "On Done Click: value = $value",
            Toast.LENGTH_SHORT
          )
            .show()
        }
      )
    )
  }
}