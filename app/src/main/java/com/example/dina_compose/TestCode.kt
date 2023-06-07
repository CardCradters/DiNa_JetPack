package com.example.dina_compose

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dina_compose.component.NamecardView
import com.example.dina_compose.ui.theme.DiNa_ComposeTheme
import com.example.dina_compose.ui.theme.verticalGradientBrush

@Composable
fun TestCode()
{
  val placeholders = listOf("Email", "Telephone", "FAX", "Mobile", "Website")
  val scrollState = rememberLazyListState()

  Column(
    modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Box(
      modifier = Modifier,
      contentAlignment = BottomCenter
    ) {
      NamecardView()
      ProfilePicture()
    }
    Card(
      modifier = Modifier
        .padding(top = 66.dp)
        .fillMaxWidth(),
      shape = RoundedCornerShape(8.dp),
      elevation = 5.dp,
    ) {
      Column(
        modifier = Modifier
          .padding(all = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
      ) {
        Text(
          text = "Username",
          style = MaterialTheme.typography.subtitle1,
        )
        Text(
          modifier = Modifier.padding(top = 8.dp),
          text = "+62 89923234819",
          style = MaterialTheme.typography.subtitle1,
        )
      }
    }

    Column(
      Modifier
        .padding(top = 24.dp)
        .fillMaxSize()
    ) {
      Text(
        "Company",
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium
      )
      Spacer(Modifier.height(16.dp))

      LazyColumn(
        modifier = Modifier
          .fillMaxSize(),
        state = scrollState
      ) {
        item {
          Card(
            shape = RoundedCornerShape(8.dp),
            elevation = 5.dp,
          ) {
            Column(
              horizontalAlignment = Alignment.CenterHorizontally,
              verticalArrangement = Arrangement.Center,
            ) {
              DetailHead(times = 5, placeholderTexts = placeholders)
            }
          }
        }
      }
    }
  }
}

@Composable
fun ProfilePicture()
{
  val context = LocalContext.current.applicationContext
  Box(
    modifier = Modifier
      .offset(y = 50.dp),
    contentAlignment = Alignment.BottomEnd,
  ) {
    Image(
      painter = painterResource(id = R.drawable.edi),
      contentDescription = "Profile Picture",
      Modifier
        .size(140.dp)
        .clip(shape = CircleShape)
        .border(width = 2.dp, color = Color.White, shape = CircleShape)
        .background(color = Color.Black.copy(alpha = ContentAlpha.medium))
        .shadow(elevation = 5.dp, shape = CircleShape)
    )
    IconButton(
      onClick = {
        Toast.makeText(context, "Click!", Toast.LENGTH_SHORT).show()
      }
    ) {
      Icon(
        modifier = Modifier
          .clip(shape = CircleShape)
          .background(color = Color.Black)
          .border(width = 1.dp, color = Color.White, shape = CircleShape)
          .padding(4.dp),
        imageVector = Icons.Outlined.Edit,
        tint = Color.White,
        contentDescription = "Edit"
      )
    }
  }
}

@Composable
fun DetailHead(times: Int, placeholderTexts: List<String>)
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
    painterResource(id = R.drawable.baseline_account_circle_24)
  )

  Column() {
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
        modifier = Modifier.fillMaxWidth(),
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
              contentDescription = null
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