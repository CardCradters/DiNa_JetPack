package com.example.dina_compose.component

import android.content.Context
import android.widget.Toast
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.dina_compose.R

@Composable
fun BottomBar(navController: NavHostController, contextForToast: Context,onShareClicked: () -> Unit)
{
  // items list
  val bottomMenuItemsList = prepareBottomMenu()
  var selectedItem by remember { mutableStateOf("home_screen") }

  BottomNavigation(
    backgroundColor = MaterialTheme.colors.primary,
  ) {
    bottomMenuItemsList.forEach { menuItem ->
      BottomNavigationItem(
        selected = (selectedItem == menuItem.label),
        onClick = {
          selectedItem = menuItem.label
          Toast.makeText(contextForToast, menuItem.label, Toast.LENGTH_SHORT)
          when (menuItem.label) {
            "Home" -> navController.navigate("home_screen")
            "Profile" -> navController.navigate("profile_screen")
            "Storage" -> navController.navigate("storage_screen")
            "Share" -> onShareClicked()
            else -> Unit
          }
        },
        icon = {
          Icon(painter = menuItem.icon, contentDescription = menuItem.label)
        }
      )
    }
  }
}

@Composable
private fun prepareBottomMenu(): List<BottomMenuItem>
{
  val bottomMenuItemsList = arrayListOf<BottomMenuItem>()
  // add menu items
  bottomMenuItemsList.add(
    BottomMenuItem(label = "Home", icon = painterResource(id = R.drawable.baseline_home_24))
  )
  bottomMenuItemsList.add(
    BottomMenuItem(label = "Share", icon = painterResource(id = R.drawable.baseline_send_24))
  )
  bottomMenuItemsList.add(
    BottomMenuItem(label = "Add", icon = painterResource(id = R.drawable.baseline_qr_code_scanner_24))
  )
  bottomMenuItemsList.add(
    BottomMenuItem(label = "Storage", icon = painterResource(id = R.drawable.baseline_folder_shared_24))
  )
  bottomMenuItemsList.add(
    BottomMenuItem(label = "Profile", icon = painterResource(id = R.drawable.baseline_account_circle_24))
  )


  return bottomMenuItemsList
}

data class BottomMenuItem(val label: String, val icon: Painter)

