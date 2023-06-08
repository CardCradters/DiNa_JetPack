package com.example.dina_compose.screen.profile

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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dina_compose.BottomBar
import com.example.dina_compose.BottomSheet
import com.example.dina_compose.component.DetailHead
import com.example.dina_compose.component.DetailItems
import com.example.dina_compose.component.NamecardView
import com.example.dina_compose.component.ProfilePicture
import com.example.dina_compose.component.TopAppBar
import com.example.dina_compose.ui.theme.DiNa_ComposeTheme
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
  val brush =
    androidx.compose.ui.graphics.Brush.linearGradient(
      listOf(
        Color(0xFF83B9E2),
        Color(0xFFFFFFFF)
      )
    )
  val contextForToast = LocalContext.current
  val scrollState = rememberLazyListState()
  val users by viewModel.users.collectAsState(emptyList())

  LaunchedEffect(Unit) {
    viewModel.fetchUsers(contextForToast)
  }


  Scaffold(
    scaffoldState = scaffoldState,
    modifier = Modifier.background(brush),
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
      BottomBar(contextForToast = contextForToast)
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
          contextForToast = contextForToast,
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
              NamecardView()
              ProfilePicture()
            }

            Card(
              modifier = Modifier
                .padding(top = 66.dp)
                .fillMaxWidth(),
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
                  text = "Username",
                  style = MaterialTheme.typography.subtitle1,
                )
                Text(
                  modifier = Modifier.padding(top = 8.dp),
                  text = "+62 89923234819",
                  style = MaterialTheme.typography.subtitle1,
                )
              }
            }

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
                Spacer(Modifier.height(16.dp))

                LazyColumn(
                  modifier = Modifier
                    .fillMaxSize(),
                  verticalArrangement = Arrangement.Center,
                  state = scrollState
                ) {
                  item {
                    DetailHead()
                  }
                  items(5) {
                    DetailItems()
                  }
                  item {
                    Divider(
                      modifier = Modifier
                        .padding(bottom = 16.dp)
                        .fillMaxWidth()
                        .height(24.dp)
                        .clip(shape = RoundedCornerShape(bottomStart = 8.dp,
                          bottomEnd = 8.dp)),
                      color = Color.White.copy(alpha = ContentAlpha.high)
                    )
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
fun ProfileView()
{
  val navController = rememberNavController()
  val viewModel =
    ProfileViewModel() // Provide a mock or dummy implementation of HomeViewModel
  DiNa_ComposeTheme(darkTheme = false) {
    Profile(navController = navController, viewModel = viewModel)
  }
}
