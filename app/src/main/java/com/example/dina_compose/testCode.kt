package com.example.dina_compose

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dina_compose.ui.theme.DiNa_ComposeTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TestCode(
  coroutineScope: CoroutineScope,
  scaffoldState: BottomSheetScaffoldState,
  contextForToast: Context,
)
{
  // items list
  val bottomSheetItemsList = prepareBottomSheet()

  LazyColumn(
    Modifier.padding(bottom = 56.dp)
  ) {
    items(bottomSheetItemsList) { sheetItem ->
      ListItem(
        modifier = Modifier.clickable {
          Toast.makeText(contextForToast, "Item ", Toast.LENGTH_SHORT).show()

          coroutineScope.launch {
            scaffoldState.bottomSheetState.collapse()
          }
        },
        text = {
          Text(text = sheetItem.label)
        },
        icon = {
          Icon(
            painter = sheetItem.icon,
            contentDescription = sheetItem.label
          )
        }
      )
    }
  }
}

@Composable
private fun prepareBottomSheet(): List<BottomItem>
{
  val bottomSheetItemsList = arrayListOf<BottomItem>()
  // add menu items
  bottomSheetItemsList.add(
    BottomItem(
      label = "Settings", icon = painterResource(
        id = R.drawable
          .baseline_settings_24
      )
    )
  )
  bottomSheetItemsList.add(
    BottomItem(label = "About DiNa", icon = painterResource(id = R.drawable.baseline_info_24))
  )
  bottomSheetItemsList.add(
    BottomItem(label = "Help", icon = painterResource(id = R.drawable.baseline_help_24))
  )
  bottomSheetItemsList.add(
    BottomItem(label = "Logout", icon = painterResource(id = R.drawable.baseline_logout_24))
  )

  return bottomSheetItemsList
}

data class BottomItem(val label: String, val icon: Painter)

@OptIn(ExperimentalMaterialApi::class)
@Preview()
@Composable
fun TestCodePreview()
{
  DiNa_ComposeTheme(darkTheme = false) {
    TestCode(
      coroutineScope = rememberCoroutineScope(),
      scaffoldState = rememberBottomSheetScaffoldState(),
      contextForToast = LocalContext.current.applicationContext
    )
  }
}