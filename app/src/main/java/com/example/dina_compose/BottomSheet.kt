package com.example.dina_compose

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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