package com.example.dina_compose.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun NavigationDrawer(
  coroutineScope: CoroutineScope, scaffoldState: ScaffoldState
)
{
  Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) { // text
    Text(text = "Your UI Here")
    // gap between text and button
    Spacer(modifier = Modifier.height(height = 32.dp))
    // button
    Button(onClick = { // close the drawer
      coroutineScope.launch {
        scaffoldState.drawerState.close()
      }
    }) {
      Text(text = "Close Drawer")
    }
  }
}