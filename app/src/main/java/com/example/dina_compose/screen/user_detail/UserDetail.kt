package com.example.dina_compose.screen.user_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.dina_compose.R
import com.example.dina_compose.component.BottomBar
import com.example.dina_compose.component.BottomSheet
import com.example.dina_compose.component.DetailUser
import com.example.dina_compose.component.TopAppBar
import com.example.dina_compose.data.ProfileRequest
import com.example.dina_compose.screen.home.HomeViewModel
import com.example.dina_compose.screen.profile.ProfileViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UserDetail(
  navController: NavHostController, uid: String?,name: String?,
  viewModel: UserDetailViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
)
{
  val scaffoldState = rememberScaffoldState()
  val sheetState = rememberBottomSheetScaffoldState()
  val coroutineScope = rememberCoroutineScope()
  val contextForToast = LocalContext.current
  val scrollState = rememberLazyListState()
  val userDetail: ProfileRequest? by viewModel.userDetail.collectAsState(null)
  val placeholders = listOf(
    if (userDetail?.email.isNullOrEmpty()) "Email" else userDetail?.email,
    if (userDetail?.phoneFaxCompany.isNullOrEmpty()) "Telephone" else userDetail?.phoneFaxCompany,
    if (userDetail?.phoneMobileCompany.isNullOrEmpty()) "FAX" else userDetail?.phoneMobileCompany,
    if (userDetail?.workplaceUri.isNullOrEmpty()) "Mobile" else userDetail?.workplaceUri,
    "Website"
  )

  var openDialog by remember { mutableStateOf(false) }

  LaunchedEffect(uid) {
    if (!uid.isNullOrEmpty()) {
      viewModel.fetchUserDetail(contextForToast, query = uid)
    }
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
      BottomBar(contextForToast = contextForToast, navController = navController,
        onShareClicked = { openDialog = true }, viewModel = ProfileViewModel(savedStateHandle = SavedStateHandle())
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
          contextForToast = contextForToast,
          navController = navController ,
          viewModel = HomeViewModel()
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
              modifier = Modifier,
              contentAlignment = Alignment.BottomCenter
            ) {
              namecard(viewModel = viewModel)
              profilePict( profileRequest =
              ProfileRequest("","","","","","","" +
                  "","","","","","","","",""))
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
                  userDetail?.phoneNumber ?: "",
                  style = MaterialTheme.typography.subtitle1
                )

                Text(
                  userDetail?.email ?: "",
                  modifier = Modifier.padding(top = 8.dp),
                  style = MaterialTheme.typography.subtitle2
                )
              }}

            Column(
              Modifier
                .padding(top = 24.dp)
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
                      DetailUser(times = 5, placeholderTexts = placeholders as List<String>,
                        viewModel = viewModel)
                    }
                  }
                }
              }
            }
          }
        }
  })
}}



@Composable
fun namecard(viewModel: UserDetailViewModel)
{
  val contextForToast = LocalContext.current
  val userDetail: ProfileRequest? by viewModel.userDetail.collectAsState(null)
  LaunchedEffect(Unit) {
    viewModel.fetchUserDetail(contextForToast, query = String())
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
    val Company = if (userDetail?.workplace.isNullOrEmpty()) {
      "Company"
    } else {
      userDetail?.workplace
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
        val name = if (userDetail?.name.isNullOrEmpty()) {
          "Username"
        } else {
          userDetail?.name
        }
        name?.let {
          Text(
            text = name,
            style = MaterialTheme.typography.h2,
            color = Color.White,
            textDecoration = TextDecoration.Underline,
          )
          val job = if (userDetail?.job_title.isNullOrEmpty()) {
            "Job Title"
          } else {
            userDetail?.job_title
          }
          job?.let {
            Text(
              text = job,
              style = MaterialTheme.typography.subtitle2,
              color = Color.White
            )

          }
        }
      }}}}



@Composable
fun profilePict(profileRequest: ProfileRequest ) {

  val context = LocalContext.current

  val painter = run {
    if (!profileRequest.filename.isNullOrEmpty()) {
      rememberImagePainter(data = profileRequest.filename)
    } else {
      painterResource(id = R.drawable.baseline_account_circle_24)
    }
  }


  Box(
    modifier = Modifier.offset(y = 50.dp),
    contentAlignment = Alignment.BottomEnd,
  ) {
    Image(
      painter = painter,
      contentDescription = "Profile Picture",
      modifier = Modifier
        .size(140.dp)
        .clip(CircleShape)
        .border(width = 2.dp, color = Color.White, shape = CircleShape)
        .background(color = Color.Black.copy(alpha = ContentAlpha.medium))
        .shadow(elevation = 5.dp, shape = CircleShape)
    )

//    IconButton(
//      onClick = {
//        galleryLauncher.launch("image/*")
//      }
//    ) {
//      Icon(
//        modifier = Modifier
//          .clip(CircleShape)
//          .background(color = Color.Black)
//          .border(width = 1.dp, color = Color.White, shape = CircleShape)
//          .padding(4.dp),
//        imageVector = Icons.Outlined.Edit,
//        tint = Color.White,
//        contentDescription = "Edit"
//      )
  }
}

