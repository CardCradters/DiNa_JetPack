package com.example.dina_compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MySnackbar(snackbarHostState: SnackbarHostState)
{
  SnackbarHost(
    hostState = snackbarHostState,
    snackbar = { data ->
      Snackbar(
        modifier = Modifier.padding(all = 8.dp),
        action = {
          Text(
            modifier = Modifier.clickable {
              snackbarHostState.currentSnackbarData?.performAction()
            },
            text = data.actionLabel!!,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
          )
        }
      ) {
        Text(text = data.message)
      }
    }
  )
}
