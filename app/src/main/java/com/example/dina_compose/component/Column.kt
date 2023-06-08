package com.example.dina_compose.component

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dina_compose.R
import com.example.dina_compose.data.ProfileRequest
import com.example.dina_compose.screen.home.HomeViewModel
import com.example.dina_compose.ui.theme.DiNa_ComposeTheme

@Composable
fun column()
{
  var searchText by remember { mutableStateOf("") }
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
fun DetailHead(placeholder: String = "Fill the Blank")
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
          contentAlignment = Center
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
          contentAlignment = Center
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

@Composable
fun DetailItems(placeholder: String = "Fill The Blank")
{
  var value by remember { mutableStateOf("") }
  val context = LocalContext.current.applicationContext
  val focusManager = LocalFocusManager.current

  BasicTextField(
    value = value,
    onValueChange = { newText ->
      value = newText
    },
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
        Box(
          modifier = Modifier,
          contentAlignment = CenterEnd
        ) {
          if (value.isEmpty())
          {
            Text(
              text = placeholder,
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

  BasicTextField(
    value = value,
    onValueChange = { newText ->
      value = newText
    },
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
        Box(
          modifier = Modifier,
          contentAlignment = CenterEnd
        ) {
          if (value.isEmpty())
          {
            Text(
              text = placeholder,
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

  BasicTextField(
    value = value,
    onValueChange = { newText ->
      value = newText
    },
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
        Box(
          modifier = Modifier,
          contentAlignment = CenterEnd
        ) {
          if (value.isEmpty())
          {
            Text(
              text = placeholder,
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

  BasicTextField(
    value = value,
    onValueChange = { newText ->
      value = newText
    },
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
        Box(
          modifier = Modifier,
          contentAlignment = CenterEnd
        ) {
          if (value.isEmpty())
          {
            Text(
              text = placeholder,
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

  BasicTextField(
    value = value,
    onValueChange = { newText ->
      value = newText
    },
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
        Box(
          modifier = Modifier,
          contentAlignment = CenterEnd
        ) {
          if (value.isEmpty())
          {
            Text(
              text = placeholder,
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

  BasicTextField(
    value = value,
    onValueChange = { newText ->
      value = newText
    },
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
        Box(
          modifier = Modifier,
          contentAlignment = CenterEnd
        ) {
          if (value.isEmpty())
          {
            Text(
              text = placeholder,
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

@Composable
fun ProfileForm(viewModel:HomeViewModel, onValueChange: (ProfileRequest) -> Unit) {
  val profile:ProfileRequest? by viewModel.profile.collectAsState(null)
    Column(
    modifier = Modifier.padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    OutlinedTextField(
      value = profile?.email ?: "",
      onValueChange = { profile?.let { it1 -> onValueChange(it1.copy(email = it)) } },
      label = { Text("Enter Your Email") }
    )

    OutlinedTextField(
      value = profile?.job_title ?: "",
      onValueChange = { profile?.let { it1 -> onValueChange(it1.copy(job_title = it)) } },
      label = { Text("Enter Your jobTitle") }
    )

    OutlinedTextField(
      value = profile?.workplace ?: "",
      onValueChange = { profile?.let { it1 -> onValueChange(it1.copy(workplace = it)) } },
      label = { Text("Enter Your workplace") }
    )

    OutlinedTextField(
      value = profile?.workplaceUri ?: "",
      onValueChange = { profile?.let { it1 -> onValueChange(it1.copy(workplaceUri = it)) } },
      label = { Text("Enter Your workplaceUri") }
    )

    OutlinedTextField(
      value = profile?.emailCompany ?: "",
      onValueChange = { profile?.let { it1 -> onValueChange(it1.copy(emailCompany = it)) } },
      label = { Text("Enter Your emailCompany") }
    )
  }
}



@Preview(showBackground = true)
@Composable
fun TestCodePreview()
{
  DiNa_ComposeTheme(darkTheme = false) {
    column()
  }
}