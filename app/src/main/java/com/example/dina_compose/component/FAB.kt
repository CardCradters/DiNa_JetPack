package com.example.dina_compose.component

import android.content.Context
import android.widget.Toast
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable

@Composable
fun FAB(contextForToast: Context)
{
  FloatingActionButton(onClick = {
    Toast.makeText(contextForToast, "FAB", Toast.LENGTH_SHORT).show()
  }) {
    Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
  }

}