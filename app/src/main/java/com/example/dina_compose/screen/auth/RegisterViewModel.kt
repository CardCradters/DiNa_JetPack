package com.example.dina_compose.screen.auth


import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.dina_compose.R
import com.example.dina_compose.api.ApiConfig
import com.example.dina_compose.common.UiState
import com.example.dina_compose.data.RegRequest
import com.example.dina_compose.data.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel : ViewModel() {
  private val _updateRegis: MutableState<UiState<String>> = mutableStateOf(UiState.Empty)
  val updateRegis: State<UiState<String>>
    get() = _updateRegis

  fun registerUser(
    name: String,
    phoneNumber: String,
    email: String,
    password: String,
    context: Context,
    callback: (Boolean, String) -> Unit = { _, _ -> }
  ) {
    _updateRegis.value = UiState.Loading
    try {
      val result = ApiConfig.registerApiService.regisUser(
        RegRequest(
          name = name,
          phoneNumber = phoneNumber,
          email = email,
          password = password
        )
      )

      result.enqueue(object : Callback<RegisterResponse> {
        override fun onResponse(
          call: Call<RegisterResponse>,
          response: Response<RegisterResponse>
        ) {
          when (response.code()) {
            201 -> {
              _updateRegis.value = UiState.Success(response.body()?.message.toString())
            }

            400 -> {
              _updateRegis.value =
                UiState.Error(context.getString(R.string.account_register_already))
            }

            else -> {
              _updateRegis.value = UiState.Error(context.getString(R.string.server_failed))
            }
          }
        }

        override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
          _updateRegis.value = UiState.Error(t.message.toString())
        }
      })

    } catch (
      e: Exception
    ) {
      Log.e("eror", e.message.toString())
    }
  }
}