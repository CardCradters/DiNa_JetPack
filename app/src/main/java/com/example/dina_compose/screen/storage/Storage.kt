package com.example.dina_compose.screen.storage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavHostController
import com.example.dina_compose.component.BottomBar
import com.example.dina_compose.component.BottomSheet
import com.example.dina_compose.component.CardListItem
import com.example.dina_compose.component.Categories
import com.example.dina_compose.component.NamecardView
import com.example.dina_compose.component.SearchBar
import com.example.dina_compose.component.TopAppBar
import com.example.dina_compose.screen.home.HomeViewModel
import com.example.dina_compose.screen.profile.ProfileViewModel
import com.example.dina_compose.screen.user_detail.UserDetailViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Storage(
  navController: NavHostController,
  viewModel: StorageViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
)
{
  val scaffoldState = rememberScaffoldState()
  val sheetState = rememberBottomSheetScaffoldState()
  val coroutineScope = rememberCoroutineScope()
  val context = LocalContext.current
  val scrollState = rememberLazyListState()
  val allCard by viewModel.allCard.collectAsState(emptyList())
  val starCard by viewModel.starCard.collectAsState(emptyList())
  val companyCard by viewModel.companyCard.collectAsState(emptyList())
  val searchCard by viewModel.searchCard.collectAsState(emptyList())
  var queryState by remember { mutableStateOf("") }
  var selectedIndex by remember { mutableIntStateOf(0) }
  var openDialog by remember { mutableStateOf(false) }
  val loadingState = remember { mutableStateOf(false) }

  LaunchedEffect(Unit) {
    loadingState.value = true
    viewModel.fetchAllCard(context)
    loadingState.value = false
  }


  Scaffold(
    scaffoldState = scaffoldState,
    topBar = {
      TopAppBar {
        coroutineScope.launch {
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
      BottomBar(navController = navController,
        contextForToast = context,
        onShareClicked = { openDialog = true },
        viewModel = ProfileViewModel(savedStateHandle = SavedStateHandle())
      )
    },
  ) { innerPadding ->
    BottomSheetScaffold(
      scaffoldState = sheetState,
      sheetBackgroundColor = MaterialTheme.colors.primary,
      sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
      sheetContent = {
        BottomSheet(
          coroutineScope = coroutineScope,
          scaffoldState = sheetState,
          contextForToast = context,
          navController = navController,
          viewModel = HomeViewModel()
        )
      },
      content = {
        Column(
          modifier = Modifier
            .padding(innerPadding)
            .padding(horizontal = 24.dp),
          verticalArrangement = Arrangement.Center,
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
          ) {
            SearchBar(onSearch = { query ->
              queryState = query
              viewModel.performSearch(context, query)
            })
            NamecardView(viewModel = ProfileViewModel(savedStateHandle = SavedStateHandle()))
            if (loadingState.value) {
              LinearProgressIndicator(modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF83B9E2)))
            }
            Categories(
              times = 3,
              label = listOf("All", "Star", "Company"),
              selectedIndex = selectedIndex,
              onCategorySelected = { index ->
                selectedIndex = index
                when (index)
                {
                  0 -> viewModel.fetchAllCard(context)
                  1 -> viewModel.fetchStarCard(context)
                  2 -> viewModel.fetchCompanyCard(context)
                }
              }
            )

            Box {
              Column(
                Modifier
                  .padding(top = 24.dp)
                  .padding(horizontal = 16.dp)
                  .fillMaxSize()
              ) {
                Text(
                  text = when (selectedIndex)
                  {
                    0 -> "All Namecard"
                    1 -> "Star Namecard"
                    2 -> "Company Namecard"
                    else -> ""
                  },
                  fontSize = 14.sp,
                  fontWeight = FontWeight.Medium
                )

                Spacer(Modifier.height(16.dp))

                LazyColumn(
                  modifier = Modifier.fillMaxSize(),
                  verticalArrangement = Arrangement.spacedBy(space = 8.dp),
                  state = scrollState
                ) {
                  val itemsToDisplay = if (queryState.isEmpty())
                  {
                    when (selectedIndex)
                    {
                      0 -> allCard
                      1 -> starCard
                      2 -> companyCard
                      else -> emptyList()
                    }
                  } else
                  {
                    searchCard
                  }
                  items(itemsToDisplay) { user ->
                    CardListItem(user,context, viewModel= UserDetailViewModel(), navController)
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













