package com.example.dina_compose.component

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dina_compose.R
import com.example.dina_compose.screen.home.HomeViewModel
import com.example.dina_compose.ui.theme.DiNa_ComposeTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheet(
  coroutineScope: CoroutineScope,
  scaffoldState: BottomSheetScaffoldState,
  contextForToast: Context,
  viewModel: HomeViewModel,
  navController: NavController
)
{
  // items list
  val bottomSheetItemsList = prepareBottomSheet()

  LazyColumn(
    Modifier
      .padding(bottom = 56.dp)
      .padding(top = 20.dp)
  ) {
    items(bottomSheetItemsList) { sheetItem ->
      if (sheetItem.label == "Logout")
      {
        ListItem(
          modifier = Modifier.clickable {
            viewModel.signOut {
              navController.navigate("login_screen")
              Toast.makeText(
                contextForToast,
                "Logout successful",
                Toast.LENGTH_SHORT
              ).show()
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
      } else if (sheetItem.label == "About DiNa")
      {
        ListItem(
          modifier = Modifier.clickable {
            navController.navigate("about") // Navigate to the "About" screen
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
      } else
      {
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
}

@Composable
private fun prepareBottomSheet(): List<BottomSheetItem>
{
  val bottomSheetItemsList = arrayListOf<BottomSheetItem>()
  // add menu items
//  bottomSheetItemsList.add(
//    BottomSheetItem(
//      label = "Settings", icon = painterResource(
//        id = R.drawable.baseline_settings_24
//      )
//    )
//  )
  bottomSheetItemsList.add(
    BottomSheetItem(
      label = "About DiNa",
      icon = painterResource(id = R.drawable.baseline_info_24)
    )
  )
  bottomSheetItemsList.add(
    BottomSheetItem(
      label = "Help",
      icon = painterResource(id = R.drawable.baseline_help_24)
    )
  )
  bottomSheetItemsList.add(
    BottomSheetItem(
      label = "Logout",
      icon = painterResource(id = R.drawable.baseline_logout_24)
    )
  )


  return bottomSheetItemsList
}

data class BottomSheetItem(val label: String, val icon: Painter)

