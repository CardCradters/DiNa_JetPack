package com.example.dina_compose.screen.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dina_compose.api.ApiConfigOcr
import com.example.dina_compose.api.ApiServiceOcr
import com.example.dina_compose.data.RecognitionResponse
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody

class OcrViewModel(private val apiConfigOcr: ApiConfigOcr) : ViewModel() {
  private val textRecognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

  private val apiServiceOcr: ApiServiceOcr = apiConfigOcr.getApiService()

  fun scanImageForText(
    image: InputImage,
    onSuccess: (RecognitionResponse) -> Unit,
    onFailure: (Exception) -> Unit
  ) {
    viewModelScope.launch {
      try {
        val visionText = textRecognizer.process(image).await()
        val extractedText = visionText.text

        val filePart = createMultipartBodyFromText(extractedText)
        filePart?.let {
          val response = apiServiceOcr.recognizeImage(it)
          if (response.isSuccessful) {
            val recognitionResponse = processRecognitionResponse(response.body())
            onSuccess(recognitionResponse)
          } else {
            onFailure(Exception("Recognition failed."))
          }
        } ?: onFailure(Exception("File part is null."))
      } catch (e: Exception) {
        onFailure(e)
      }
    }
  }

  private suspend fun createMultipartBodyFromText(text: String): MultipartBody.Part? {
    val requestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), text)
    return MultipartBody.Part.createFormData("file", null, requestBody)
  }


  private fun processRecognitionResponse(response: RecognitionResponse?): RecognitionResponse {
    // Perform any necessary processing or conversion of the response here
    // and return the processed result
    return response ?: throw Exception("Recognition response is null.")
  }
}


