package com.example.dina_compose

import android.content.Context
import android.widget.Toast
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.dina_compose.ui.theme.DiNa_ComposeTheme

@Composable
fun BottomBar(contextForToast: Context)
{
  // items list
  val bottomMenuItemsList = prepareBottomMenu()
  var selectedItem by remember { mutableStateOf("Home") }

  BottomNavigation(
    backgroundColor = MaterialTheme.colors.background,
  ) {
    bottomMenuItemsList.forEach { menuItem ->
      BottomNavigationItem(
        selected = (selectedItem == menuItem.label),
        onClick = {
          selectedItem = menuItem.label
          Toast.makeText(contextForToast, menuItem.label, Toast.LENGTH_SHORT).show()
        },
        icon = {
          Icon(imageVector = menuItem.icon, contentDescription = menuItem.label)
        }
      )
    }
  }
}

private fun prepareBottomMenu(): List<BottomMenuItem>
{
  val bottomMenuItemsList = arrayListOf<BottomMenuItem>()
  // add menu items
  bottomMenuItemsList.add(BottomMenuItem(label = "Home", icon = Icons.Filled.Home))
  bottomMenuItemsList.add(BottomMenuItem(label = "Share", icon = Icons.Filled.Share))
  bottomMenuItemsList.add(BottomMenuItem(label = "Add", icon = Icons.Filled.Add))
  bottomMenuItemsList.add(BottomMenuItem(label = "Storage", icon = Icons.Filled.List))
  bottomMenuItemsList.add(BottomMenuItem(label = "Profile", icon = Icons.Filled.Person))

  return bottomMenuItemsList
}

data class BottomMenuItem(val label: String, val icon: ImageVector)

@Preview()
@Composable
fun BottomBarPreview()
{
  DiNa_ComposeTheme(darkTheme = false) {
    BottomBar(contextForToast = LocalContext.current.applicationContext)
  }
}