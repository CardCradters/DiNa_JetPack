package com.example.dina_compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dina_compose.ui.theme.DiNa_ComposeTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ScaffoldLayout()
{
  val scaffoldState = rememberScaffoldState()
  val sheetState = rememberBottomSheetScaffoldState()
  val coroutineScope = rememberCoroutineScope()
  val contextForToast = LocalContext.current.applicationContext
//  val bottomBarVisible = remember { mutableStateOf(true) }

  Scaffold(
    scaffoldState = scaffoldState,
    topBar = {
      TopAppBar {
        coroutineScope.launch {
//          sheetState.bottomSheetState.expand()
          if (sheetState.bottomSheetState.isCollapsed)
          {
            sheetState.bottomSheetState.expand()
          } else
          {
            sheetState.bottomSheetState.collapse()
          }
        }
      }
    },
    bottomBar = {
        BottomBar(contextForToast = contextForToast)
//      if (bottomBarVisible.value)
//      {
//        BottomBar(contextForToast = contextForToast)
//      }
    },
  ) { innerPadding ->
    BottomSheetScaffold(scaffoldState = sheetState,
      sheetBackgroundColor = MaterialTheme.colors.background,
      sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
      sheetPeekHeight = 0.dp,
      sheetContent = {
        BottomSheet(
          coroutineScope = coroutineScope,
          scaffoldState = sheetState,
          contextForToast = contextForToast,
        )
      },
      content = {
        //    Activity
        Column(
          modifier = Modifier.padding(innerPadding),
          verticalArrangement = Arrangement.Center,
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          HomeScreen()
        }
      }
    )
  }
//  LaunchedEffect(sheetState.bottomSheetState.isExpanded) {
//    bottomBarVisible.value = !sheetState.bottomSheetState.isExpanded
//  }
}

@Preview()
@Composable
fun ScaffoldLayoutPreview()
{
  DiNa_ComposeTheme(darkTheme = false) {
    ScaffoldLayout()
  }
}


