package com.example.dina_compose.screen.home


import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dina_compose.UserPreference
import com.example.dina_compose.api.ApiConfig
import com.example.dina_compose.common.DataState
import com.example.dina_compose.common.safeApiCall
import com.example.dina_compose.data.ProfileRequest
import com.example.dina_compose.data.UserRequest
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class HomeViewModel(
) : ViewModel() {
  private val _users = MutableStateFlow<List<UserRequest>>(emptyList())
  val users: StateFlow<List<UserRequest>> = _users
  private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

  private val _searchResults = MutableStateFlow<List<UserRequest>>(emptyList())
  val searchResult: StateFlow<List<UserRequest>> = _searchResults

//  val context = LocalContext.current
//  private val userPreference: UserPreference = UserPreference(context)
  private val _stared = MutableStateFlow<ProfileRequest?>(null)
  val stared:StateFlow<ProfileRequest?>  = _stared

  private val _profile = MutableStateFlow<ProfileRequest?>(null)
  val profile: StateFlow<ProfileRequest?> = _profile




  fun fetchUsers(context: Context) = viewModelScope.launch {
    when (val result = safeApiCall { ApiConfig.apiService(context).getUsers() }) {
      DataState.Loading -> Unit
      is DataState.Error -> Log.e("salah", result.message)
      is DataState.Result -> {
        //set data user from api
        _users.tryEmit(result.data.payload.datas)
      }
    }
  }

  fun signOut(callback:()->Unit={})=viewModelScope.launch {
    firebaseAuth.signOut()
    callback()
  }

  fun performSearch(context: Context, query: String) = viewModelScope.launch {
    when (val result = safeApiCall { ApiConfig.apiService(context).search(query)}) {
      DataState.Loading -> Unit
      is DataState.Error -> Log.e("salah", result.message)
      is DataState.Result -> {
        _searchResults.tryEmit(result.data.payload.datas)
      }
    }
  }

  fun starred(context: Context, uid: String, isStarred: Boolean) = viewModelScope.launch {


    val profileRequest = ProfileRequest(
      uid = uid,
      name = "",
      job_title = "",
      workplace = "",
      stared = isStarred,
      filename = "",
      storagePath = "",
      password ="",
      workplaceUri ="",
      addressCompany ="",
      emailCompany ="",
      email ="",
      phoneMobileCompany ="",
      phoneFaxCompany ="",
      phoneTelpCompany ="",
      phoneNumber ="",
    )

    when (val result = safeApiCall { ApiConfig.apiService(context).starred(uid, profileRequest)
    }) {

      DataState.Loading -> Unit
      is DataState.Error -> Log.e("salah", result.message)
      is DataState.Result -> {
        // Handle the result accordingly
        if (result.data.payloadx?.datas != null) {
          // Update the stared list with the updated data
          _stared.tryEmit(result.data.payloadx.datas)

//          userPreference.saveStared(isStarred)
        } else {
          // Handle the case when the payload or datas are null
          Log.e("salah", "No Saved User")
        }
      }
    }
  }


  fun fetchProfile(context: Context) = viewModelScope.launch {
    when (val result = safeApiCall { ApiConfig.apiService(context).getProfile() }) {
      DataState.Loading -> Unit
      is DataState.Error -> Log.e("salah", result.message)
      is DataState.Result -> {
        _profile.tryEmit(result.data.payloadx.datas)
      }
    }
  }



}
