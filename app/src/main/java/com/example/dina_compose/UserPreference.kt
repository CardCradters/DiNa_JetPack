package com.example.dina_compose

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private val Context.dataStore by preferencesDataStore("bearer_token_datastore")


class UserPreference(private val context : Context)
{
  private val userDataStore = context.dataStore

  suspend fun saveStared(isStared: Boolean) {
    userDataStore.edit { preferences ->
      preferences[PreferenceKeys.STARED] = isStared
    }
  }

  val staredFlow: Flow<Boolean> = userDataStore.data
    .catch { exception ->
      if (exception is IOException) {
        // Handle IO exceptions
        emit(emptyPreferences())
      } else {
        throw exception
      }
    }
    .map { preferences ->
      preferences[PreferenceKeys.STARED] ?: false
    }
}



  object PreferenceKeys {
    val STARED = booleanPreferencesKey("stared")
  }

