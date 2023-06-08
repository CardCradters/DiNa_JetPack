package com.example.dina_compose.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.dina_compose.R

@Composable
fun DetailProfile(times: Int, placeholderTexts: List<String>)
{
  val focusManager = LocalFocusManager.current
  var compValue by remember { mutableStateOf("") }
  var addrValue by remember { mutableStateOf("") }
  val textFieldValues = remember { mutableStateListOf<String>() }
  val icons = listOf(
    painterResource(id = R.drawable.baseline_alternate_email_24),
    painterResource(id = R.drawable.baseline_phone_24),
    painterResource(id = R.drawable.baseline_fax_24),
    painterResource(id = R.drawable.baseline_phone_android_24),
    painterResource(id = R.drawable.baseline_public_24)
  )

  Column {
    TextField(
      value = compValue,
      onValueChange = { compValue = it },
      shape = RoundedCornerShape(0.dp),
      modifier = Modifier
        .fillMaxWidth(),
      textStyle = MaterialTheme.typography.subtitle1
        .copy(textAlign = TextAlign.Center),
      singleLine = true,
      placeholder = {
        Text(
          text = "Company",
          modifier = Modifier.fillMaxWidth(),
          textAlign = TextAlign.Center,
          style = MaterialTheme.typography.body1
        )
      },
      keyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Done
      ),
      keyboardActions = KeyboardActions(
        onDone = {
          focusManager.clearFocus()
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
    TextField(
      value = addrValue,
      onValueChange = { addrValue = it },
      shape = RoundedCornerShape(0.dp),
      modifier = Modifier
        .fillMaxWidth()
        .offset(y = (-16).dp),
      textStyle = MaterialTheme.typography.subtitle1
        .copy(textAlign = TextAlign.Center),
      singleLine = true,
      placeholder = {
        Text(
          text = "Office Address",
          modifier = Modifier.fillMaxWidth(),
          textAlign = TextAlign.Center,
          style = MaterialTheme.typography.body1
        )
      },
      keyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Done
      ),
      keyboardActions = KeyboardActions(
        onDone = {
          focusManager.clearFocus()
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

    repeat(times) { index ->
      var itemValue by remember { mutableStateOf("") }

      TextField(
        value = itemValue,
        onValueChange = {
          itemValue = it
          if (index >= textFieldValues.size)
          {
            textFieldValues.add(itemValue)
          } else
          {
            textFieldValues[index] = itemValue
          }
        },
        shape = RoundedCornerShape(0.dp),
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 8.dp),
        textStyle = MaterialTheme.typography.subtitle1.copy(
          textAlign = TextAlign
            .End
        ),
        singleLine = true,
        placeholder = {
          Text(
            text = placeholderTexts.getOrElse(index) { "-" },
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Normal
          )
        },
        leadingIcon = {
          if (index < icons.size)
          {
            Icon(
              painter = icons[index],
              contentDescription = null,
              tint = Color.Black
            )
          }
        },
        keyboardOptions = KeyboardOptions(
          keyboardType = KeyboardType.Text,
          imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
          onDone = {
            focusManager.clearFocus()
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