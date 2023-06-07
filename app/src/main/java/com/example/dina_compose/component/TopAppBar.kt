package com.example.dina_compose.component

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.dina_compose.ui.theme.DiNa_ComposeTheme

@Composable
fun TopAppBar(onNavigationIconClick: () -> Unit)
{
  TopAppBar(
    title = {
      Text(
        text = "DiNa",
        style = MaterialTheme.typography.h1
      )
    },
    backgroundColor = MaterialTheme.colors.primary,
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

@OptIn(ExperimentalMaterialApi::class)
@Preview()
@Composable
fun TopAppBarPreview()
{
  DiNa_ComposeTheme(darkTheme = false) {
    TopAppBar(onNavigationIconClick = { })
  }
}
