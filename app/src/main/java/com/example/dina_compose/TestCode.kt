package com.example.dina_compose

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
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
import com.example.dina_compose.component.SearchBar
import com.example.dina_compose.ui.theme.DiNa_ComposeTheme
import com.example.dina_compose.ui.theme.verticalGradientBrush

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TestCode()
{
  val sheetState = rememberBottomSheetScaffoldState()
  val coroutineScope = rememberCoroutineScope()
  val brush = verticalGradientBrush
  val context = LocalContext.current
  val scrollState = rememberLazyListState()
  var queryState by remember { mutableStateOf("") }

  val label = listOf("All", "Star", "Company")

  Column(
    modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    SearchBar(onSearch = { query ->
      queryState = query
//      viewModel.performSearch(context, query)
      // Call performSearch in the view model
    })

    NamecardView()
    Categories(times = 3, label = label)
  }
}

@Composable
fun Categories(times: Int, label: List<String>)
{
  val icons = listOf(
    painterResource(id = R.drawable.baseline_folder_shared_24),
    painterResource(id = R.drawable.baseline_star_25),
    painterResource(id = R.drawable.baseline_business_24),
  )

  Row(
    modifier = Modifier
      .padding(top = 16.dp)
      .fillMaxWidth()
      .height(72.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    repeat(times) {index ->
      Box(
        modifier = Modifier
          .fillMaxHeight()
          .width(110.dp)
          .clip(shape = RoundedCornerShape(8.dp))
          .background(color = Color.Black),
        contentAlignment = Center
      ) {
        IconButton(
          onClick = { /*TODO*/ },
          modifier = Modifier.fillMaxSize(),
        ) {
          Column(
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
            Icon(
              painter = icons[index],
              contentDescription = "All",
              modifier = Modifier
                .size(32.dp),
              tint = Color.White
            )
            Text(
              text = label[index],
              color = Color.White
            )
          }
        }
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