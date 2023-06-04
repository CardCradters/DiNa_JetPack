package com.example.dina_compose.api

import android.content.Context
import android.util.Log
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.example.dina_compose.BuildConfig
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

object ApiConfig {

  private suspend fun getTokenFromFirebase(): String? {
    return suspendCancellableCoroutine { continuation ->
      val user = FirebaseAuth.getInstance().currentUser
      val uidTask = user?.getIdToken(true)
      uidTask?.addOnCompleteListener { task ->
        if (task.isSuccessful) {
          val tokenResult = task.result?.token
          continuation.resume(tokenResult)
        } else {
          val exception = task.exception
          continuation.resumeWithException(exception ?: Exception("Failed to get token"))
        }
      }
    }
  }
  fun apiService(context: Context): ApiService {

    // Create the Collector
    val chuckerCollector = ChuckerCollector(
      context = context,
      // Toggles visibility of the notification
      showNotification = true,
      // Allows to customize the retention period of collected data
      retentionPeriod = RetentionManager.Period.ONE_HOUR
    )

    // Create the Interceptor
    val chuckerInterceptor = ChuckerInterceptor.Builder(context)
      // The previously created Collector
      .collector(chuckerCollector)
      // The max body content length in bytes, after this responses will be truncated.
      .maxContentLength(250_000L)
      // List of headers to replace with ** in the Chucker UI
      .redactHeaders("Auth-Token", "Bearer")
      // Read the whole response body even when the client does not consume the response completely.
      // This is useful in case of parsing errors or when the response body
      // is closed before being read like in Retrofit with Void and Unit types.
      .alwaysReadResponseBody(true)
      // Use decoder when processing request and response bodies. When multiple decoders are installed they
      // are applied in an order they were added.
      // Controls Android shortcut creation. Available in SNAPSHOTS versions only at the moment
      .build()

    val httpBuilder = OkHttpClient.Builder()
      .apply {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        addInterceptor(interceptor)
      }
      .addInterceptor { chain ->
        val result = runBlocking { getTokenFromFirebase() }
        val token = chain.request().newBuilder().addHeader(
          "Authorization",
          "Bearer ${result.orEmpty()}"
        ).build()
        chain.proceed(token)
      }
      .addInterceptor(chuckerInterceptor)
      .connectTimeout(15, TimeUnit.SECONDS)
      .readTimeout(20, TimeUnit.SECONDS)
      .build()

    val retrofitBuilder = Retrofit.Builder()
      .baseUrl(BuildConfig.BASE_URL)
      .addConverterFactory(
        GsonConverterFactory
          .create(
            GsonBuilder()
              .setLenient()
              .disableHtmlEscaping()
              .create()
          )
      )
      .client(httpBuilder)
      .build()

    return retrofitBuilder.create(ApiService::class.java)
  }

}