package com.example.dina_compose.component

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DetailItems(value: String, onValueChange: (String) -> Unit)
{
  var value by remember { mutableStateOf("") }
  val context = LocalContext.current.applicationContext
  val focusManager = LocalFocusManager.current

  BasicTextField(
    value = value,
    onValueChange = onValueChange,
    textStyle = TextStyle(
      fontSize = 20.sp,
      fontWeight = FontWeight.Medium,
      textAlign = TextAlign.End
    ),
    singleLine = true,
    decorationBox = { innerTextField ->
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
//        Icon(
//          Icons.Default.Search,
//          contentDescription = "Search"
//        )
        Box(
          modifier = Modifier,
          contentAlignment = Alignment.CenterEnd
        ) {
          if (value.isEmpty())
          {
            Text(
              text = value,
              textAlign = TextAlign.End
            )
          }
          innerTextField()
        }
      }
    },
    keyboardOptions = KeyboardOptions(
      keyboardType = KeyboardType.Text,
      imeAction = ImeAction.Done
    ),
    keyboardActions = KeyboardActions(
      onDone = {
//          TODO Update Actions >=> >=> >=> >=> >=> >=> >=> >=> >=>
        focusManager.clearFocus()

        Toast.makeText(
          context,
          "On Done Click: value = $value",
          Toast.LENGTH_SHORT
        )
          .show()
      }
    ),
    modifier = Modifier
      .background(color = Color.White.copy(alpha = ContentAlpha.high))
      .padding(top = 8.dp)
  )
  Divider(
    modifier = Modifier.fillMaxWidth(),
    color = Color.Black,
    thickness = 0.5.dp
  )
}