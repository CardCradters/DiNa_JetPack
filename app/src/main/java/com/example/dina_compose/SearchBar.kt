package com.example.dina_compose

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar()
{
  var value by remember {
    mutableStateOf("")
  }
  val context = LocalContext.current.applicationContext
  val focusManager = LocalFocusManager.current

  Surface(
    Modifier
        .padding(top = 16.dp)
        .padding(horizontal = 16.dp)
        .fillMaxWidth(),
    shape = RoundedCornerShape(8.dp),
    color = Color.White.copy(alpha = ContentAlpha.disabled)
  ) {
    TextField(
      value = value,
      onValueChange = { newText ->
        value = newText
      },
      leadingIcon = {
        Icon(
          Icons.Default.Search,
          contentDescription = "Search"
        )
      },
      placeholder = { Text(text = "Search here...") },
      trailingIcon = {
        if (value.isNotEmpty())
        {
          IconButton(onClick = {
            value = ""
          }) {
            Icon(
              Icons.Default.Clear,
              contentDescription = "Clear"
            )
          }
        }
      },
      singleLine = true,
      keyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Search
      ),
      keyboardActions = KeyboardActions(
        onSearch = {
//          TODO Search Actions
          focusManager.clearFocus()

          Toast.makeText(context, "On Search Click: value = $value", Toast.LENGTH_SHORT)
              .show()
        }
      ),
      colors = TextFieldDefaults.textFieldColors(
        backgroundColor = Color.Transparent,
        cursorColor = Color.Black.copy(alpha = ContentAlpha.medium),
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent
      )
    )
  }
}

@Preview(showBackground = true)
@Composable
fun SearchPreview()
{
  SearchBar(
  )
}