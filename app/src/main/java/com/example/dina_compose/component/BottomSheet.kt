package com.example.dina_compose.component

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.dina_compose.R
import com.example.dina_compose.ui.theme.DiNa_ComposeTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheet(
  coroutineScope: CoroutineScope,
  scaffoldState: BottomSheetScaffoldState,
  contextForToast: Context,
)
{
  // items list
  val bottomSheetItemsList = prepareBottomSheet()

  LazyColumn {
    items(count = 5) {
      ListItem(modifier = Modifier.clickable {
        Toast.makeText(contextForToast, "Item $it", Toast.LENGTH_SHORT).show()

        coroutineScope.launch {
          scaffoldState.bottomSheetState.collapse()
        }
      },
        text = {
          Text(text = "Item $it")
        }, icon = {
          Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = "Favorite",
            tint = MaterialTheme.colors.primary
          )
        }
      )
    }
  }
}

@Composable
private fun prepareBottomSheet(): List<BottomSheetItem>
{
  val bottomSheetItemsList = arrayListOf<BottomSheetItem>()
  // add menu items
  bottomSheetItemsList.add(
    BottomSheetItem(
      label = "Settings", icon = painterResource(
        id = R.drawable
          .baseline_settings_24
      )
    )
  )
  bottomSheetItemsList.add(
    BottomSheetItem(label = "About DiNa", icon = painterResource(id = R.drawable.baseline_info_24))
  )
  bottomSheetItemsList.add(
    BottomSheetItem(label = "Help", icon = painterResource(id = R.drawable.baseline_help_24))
  )
  bottomSheetItemsList.add(
    BottomSheetItem(label = "Logout", icon = painterResource(id = R.drawable.baseline_logout_24))
  )

  return bottomSheetItemsList
}

data class BottomSheetItem(val label: String, val icon: Painter)

@OptIn(ExperimentalMaterialApi::class)
@Preview()
@Composable
fun BottomSheetPreview()
{
  DiNa_ComposeTheme(darkTheme = false) {
    BottomSheet(
      coroutineScope = rememberCoroutineScope(),
      scaffoldState = rememberBottomSheetScaffoldState(),
      contextForToast = LocalContext.current.applicationContext
    )
  }
}