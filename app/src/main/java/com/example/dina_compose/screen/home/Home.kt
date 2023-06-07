package com.example.dina_compose.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.dina_compose.component.BottomBar
import com.example.dina_compose.component.BottomSheet
import com.example.dina_compose.component.CardListItem
import com.example.dina_compose.component.NamecardView
import com.example.dina_compose.component.SearchBar
import com.example.dina_compose.component.TopAppBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Home(
  navController: NavHostController,
  viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
  val scaffoldState = rememberScaffoldState()
  val sheetState = rememberBottomSheetScaffoldState()
  val coroutineScope = rememberCoroutineScope()
  val Brush =
    androidx.compose.ui.graphics.Brush.linearGradient(
      listOf(
        Color(0xFF83B9E2),
        Color(0xFFFFFFFF)
      )
    )
  val context = LocalContext.current
  val scrollState = rememberLazyListState()
  val users by viewModel.users.collectAsState(emptyList())
  val searchResult by viewModel.searchResult.collectAsState(emptyList())
  var queryState by remember { mutableStateOf("") }


  LaunchedEffect(Unit) {
    viewModel.fetchUsers(context)
  }


  Scaffold(
    scaffoldState = scaffoldState,
    modifier = Modifier.background(
      androidx.compose.ui.graphics.Brush.horizontalGradient(
        colors = listOf(
          Color(0xFF83B9E2),
          Color(0xFFFFFFFF)
        )
      )
    ),
    topBar = {
      TopAppBar {
        coroutineScope.launch {
          if (sheetState.bottomSheetState.isCollapsed) {
            sheetState.bottomSheetState.expand()
          } else {
            sheetState.bottomSheetState.collapse()
          }
        }
      }
    },
    bottomBar = {
      BottomBar(navController = navController,contextForToast = context)
    },
  ) { innerPadding ->
    BottomSheetScaffold(
      scaffoldState = sheetState,
      sheetBackgroundColor = MaterialTheme.colors.background,
      sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
      sheetContent = {
        BottomSheet(
          coroutineScope = coroutineScope,
          scaffoldState = sheetState,
          contextForToast = context,
          navController = navController,
          viewModel = viewModel
        )
      },
      content = {
        Column(
          modifier = Modifier.padding(innerPadding),
          verticalArrangement = Arrangement.Center,
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
          ){
            SearchBar(onSearch = { query ->
              queryState = query
              viewModel.performSearch(context, query) // Call performSearch
            })
            NamecardView()
            Text(
              "Digitize Your Network",
              fontSize = 24.sp,
              fontWeight = FontWeight.SemiBold
            )
            Box {
              Column(
                Modifier
                  .padding(all = 16.dp)
                  .fillMaxSize()
              ) {
                Text(
                  "Last Activity",
                  fontSize = 14.sp,
                  fontWeight = FontWeight.Medium
                )
                Spacer(Modifier.height(16.dp))

                LazyColumn(
                  modifier = Modifier
                    .fillMaxSize()
                    .size(30.dp)
                    .padding(16.dp),
                  verticalArrangement = Arrangement.spacedBy(space = 10.dp),
                  state = scrollState
                ) {
                  if (users.isEmpty() && queryState.isEmpty()) {
                    item {
                      Text(
                        text = "Anda belum pernah menyimpan 1 pun kontak",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                      )
                    }
                  } else {
                    val itemsToDisplay = if (queryState.isEmpty()) users else searchResult
                    items(itemsToDisplay) { user ->
                      CardListItem(user = user, context = context, viewModel = viewModel)
                    }
                  }
                }
              }
            }
          }
        }
      }
    )
  }
}

@Preview(showBackground = true)
@Composable
fun ScaffoldView() {

}
