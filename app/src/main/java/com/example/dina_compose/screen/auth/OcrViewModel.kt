package com.example.dina_compose.screen.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dina_compose.api.ApiConfigOcr
import com.example.dina_compose.api.ApiServiceOcr
import com.example.dina_compose.data.RecognitionResponse
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class OcrViewModel(private val apiConfigOcr: ApiConfigOcr) : ViewModel()
{
  private val apiServiceOcr: ApiServiceOcr = apiConfigOcr.getApiService()
  fun scanImageForText(
    imageUri: MultipartBody.Part,
    onSuccess: (RecognitionResponse) -> Unit,
    onFailure: (Exception) -> Unit
  )
  {
    viewModelScope.launch {
      try
      {
        val response = apiServiceOcr.recognizeImage(imageUri)

        if (response.isSuccessful)
        {
          val recognitionResponse = processRecognitionResponse(response.body())
          onSuccess(recognitionResponse)
        } else
        {
          onFailure(Exception("Recognition failed."))
        }
      } catch (e: Exception)
      {
        onFailure(e)
      }
    }
  }

  private fun processRecognitionResponse(response: RecognitionResponse?): RecognitionResponse
  {
    // Perform any necessary processing or conversion of the response here
    // and return the processed result
    return response ?: throw Exception("Recognition response is null.")
  }
}

