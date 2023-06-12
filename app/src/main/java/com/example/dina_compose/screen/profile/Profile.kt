package com.example.dina_compose.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.Card
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.dina_compose.component.BottomBar
import com.example.dina_compose.component.BottomSheet
import com.example.dina_compose.component.DetailProfile
import com.example.dina_compose.component.NamecardView
import com.example.dina_compose.component.ProfilePicture
import com.example.dina_compose.component.TopAppBar
import com.example.dina_compose.data.ProfileRequest
import com.example.dina_compose.data.UploadRequest
import com.example.dina_compose.screen.home.HomeViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Profile(
  navController: NavHostController,
  viewModel: ProfileViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
)
{
  val scaffoldState = rememberScaffoldState()
  val sheetState = rememberBottomSheetScaffoldState()
  val coroutineScope = rememberCoroutineScope()
  val contextForToast = LocalContext.current
  val scrollState = rememberLazyListState()
  val profile: ProfileRequest? by viewModel.profile.collectAsState(null)
  val placeholders = listOf(
    if (profile?.emailCompany.isNullOrEmpty()) "Email Company" else profile?.emailCompany,
    if (profile?.phoneFaxCompany.isNullOrEmpty()) "Telephone" else profile?.phoneFaxCompany,
    if (profile?.phoneMobileCompany.isNullOrEmpty()) "FAX" else profile?.phoneMobileCompany,
    if (profile?.workplaceUri.isNullOrEmpty()) "Mobile" else profile?.workplaceUri,
    "Website"
  )

  var openDialog by remember { mutableStateOf(false) }

  LaunchedEffect(Unit) {
    viewModel.fetchProfile(contextForToast)

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
      BottomBar(contextForToast = contextForToast, navController = navController, onShareClicked = { openDialog = true })
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
          contextForToast = contextForToast,viewModel = HomeViewModel(), navController=
          navController
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
            Box(
              contentAlignment = Alignment.BottomCenter
            ) {
              NamecardView(viewModel = viewModel)
              ProfilePicture(viewModel = viewModel, uploadRequest = UploadRequest(""))
            }

            Card(
              modifier = Modifier
                .padding(top = 66.dp)
                .fillMaxWidth().background(Color(0xFFD1D1D1)),
              shape = RoundedCornerShape(8.dp),
              elevation = 5.dp,
            ) {
              Column(
                modifier = Modifier
                  .padding(all = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
              ) {
                Text(
                  profile?.name ?: "",
                  style = MaterialTheme.typography.subtitle1
                )

                Text(
                  profile?.phoneNumber ?: "",
                  modifier = Modifier.padding(top = 8.dp),
                  style = MaterialTheme.typography.subtitle2
                )
              }}

            Box(
              modifier = Modifier
                .padding(top = 24.dp)
            ) {
              Column(
                Modifier
                  .fillMaxSize()
              ) {
                Text(
                  "Company",
                  fontSize = 14.sp,
                  fontWeight = FontWeight.Medium
                )

                LazyColumn(
                  modifier = Modifier
                    .fillMaxSize(),
                  state = scrollState
                ) {
                  item {
                    Card(
                      modifier = Modifier
                        .padding(bottom = 8.dp),
                      shape = RoundedCornerShape(8.dp),
                      elevation = 5.dp,
                    ) {
                      Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                      ) {
                        DetailProfile(times = 5, placeholderTexts = placeholders as List<String>,
                          viewModel = viewModel)
                      }
                    }
                  }
                }

              }
            }
          }
        }
      })
  }}
