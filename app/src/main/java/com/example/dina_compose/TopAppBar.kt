package com.example.dina_compose

import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp

@Composable
fun TopAppBar(onNavigationIconClick: () -> Unit)
{
  TopAppBar(
    title = {
    Text(
      text = "DiNa"
    )
  },
    backgroundColor = MaterialTheme.colors.background,
    actions = {
    IconButton(onClick = {
      onNavigationIconClick()
    }) {
      Icon(
        imageVector = Icons.Outlined.Menu, contentDescription = "navigation"
      )
    }
  }
  )
}
