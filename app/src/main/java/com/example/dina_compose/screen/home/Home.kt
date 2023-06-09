package com.example.dina_compose.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.Card
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.dina_compose.R
import com.example.dina_compose.component.BottomBar
import com.example.dina_compose.component.BottomSheet
import com.example.dina_compose.component.SearchBar
import com.example.dina_compose.component.TopAppBar
import com.example.dina_compose.component.logMessage
import com.example.dina_compose.data.ProfileRequest
import com.example.dina_compose.screen.profile.ProfileViewModel
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
  val context = LocalContext.current
  val scrollState = rememberLazyListState()
  val users by viewModel.users.collectAsState(emptyList())
  val searchResult by viewModel.searchResult.collectAsState(emptyList())
  var queryState by remember { mutableStateOf("") }
  var openDialog by remember { mutableStateOf(false) }
  val loadingState = remember { mutableStateOf(false) }

  LaunchedEffect(Unit) {
    loadingState.value = true
    viewModel.fetchUsers(context)
    loadingState.value = false
  }


  Scaffold(
    scaffoldState = scaffoldState,

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
      BottomBar(navController = navController,contextForToast = context,
        onShareClicked = { openDialog = true }, viewModel = ProfileViewModel(savedStateHandle = SavedStateHandle()))
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
          viewModel = viewModel
        )
      },
      content = {
        //    Activity
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
              viewModel.performSearch(context, query) // Call performSearch in the view model
            })
            namecardHome(viewModel = ProfileViewModel(savedStateHandle = SavedStateHandle()))
            Text(
              "Digitize Your Network",
              fontSize = 24.sp,
              fontWeight = FontWeight.SemiBold
            )

            Box {
              Column(
                Modifier
                  .padding(top = 24.dp)
                  .padding(horizontal = 16.dp)
                  .fillMaxSize()
              ) {
                Text(
                  "Last Activity",
                  fontSize = 14.sp,
                  fontWeight = FontWeight.Medium
                )

                Spacer(Modifier.height(16.dp))

                if (loadingState.value) {
                  LinearProgressIndicator(modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF83B9E2)))
                }

                LazyColumn(
                  modifier = Modifier
                    .fillMaxSize(),
                  verticalArrangement = Arrangement.spacedBy(space = 8.dp),
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
                      Card(
                        Modifier
                          .clickable {
                            navController.navigate("detail_screen/${user.uid}/${user.name}") {
                              launchSingleTop = true
                              popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                              }
                            } ?: logMessage("User detail not available")
                          }
                          .wrapContentHeight()
                          .fillMaxWidth(),
                        elevation = 3.dp,
                        shape = RoundedCornerShape(8.dp)
                      ) {
                        Row(
                          Modifier
                            .wrapContentHeight()
                            .padding(horizontal = 16.dp)
                            .padding(vertical = 16.dp),
                          verticalAlignment = Alignment.CenterVertically,
                          horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                          Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                          ) {
                            Image(
                              painter = painterResource(id = R.drawable
                                .baseline_account_circle_24),
                              contentDescription = "Profile Picture",
                              modifier = Modifier
                                .clip(shape = CircleShape)
                                .size(60.dp)
                                .background(color = MaterialTheme.colors.primary)
                                .border(
                                  width = 2.dp,
                                  color = MaterialTheme.colors.secondary,
                                  shape = CircleShape
                                )
                            )

                            Column(
                              Modifier
                                .padding(start = 8.dp),
                              verticalArrangement = Arrangement.Center
                            ) {
                              Text(
                                user.name,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                letterSpacing = 1.sp,
                                modifier = Modifier.padding(bottom = 4.dp)
                              )
                              Text(
                                user.job_title,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Normal
                              )
                              Text(
                                user.workplace,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Normal
                              )
                            }
                          }
                        }
                      }
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


@Composable
fun namecardHome(viewModel: ProfileViewModel)
{
  val contextForToast = LocalContext.current
  val profile: ProfileRequest? by viewModel.profile.collectAsState(null)
  LaunchedEffect(Unit) {
    viewModel.fetchProfile(contextForToast)
  }
  Card(
    modifier = Modifier
      .padding(vertical = 16.dp)
      .aspectRatio(328f / 207f),
    shape = RoundedCornerShape(20.dp),
    elevation = 5.dp
  ) {
    Image(
      painter = painterResource(id = R.drawable.card_1),
      contentDescription = "Card Background",
      modifier = Modifier.fillMaxSize(),
      contentScale = ContentScale.Crop,
    )
    val Company = if (profile?.workplace.isNullOrEmpty()) {
      "Company"
    } else {
      profile?.workplace
    }
    Company?.let {
      Text(
        text = Company,
        style = MaterialTheme.typography.h1,
        modifier = Modifier
          .fillMaxWidth()
          .padding(20.dp),
        color = Color.White,
      )
      Column(
        modifier = Modifier
          .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        val name = if (profile?.name.isNullOrEmpty()) {
          "Username"
        } else {
          profile?.name
        }
        name?.let {
          Text(
            text = name,
            style = MaterialTheme.typography.h2,
            color = Color.White,
            textDecoration = TextDecoration.Underline,
          )
          val job = if (profile?.job_title.isNullOrEmpty()) {
            "Job Title"
          } else {
            profile?.job_title
          }
          job?.let {
            Text(
              text = job,
              style = MaterialTheme.typography.subtitle2,
              color = Color.White
            )

          }
        }
      }}}
}
