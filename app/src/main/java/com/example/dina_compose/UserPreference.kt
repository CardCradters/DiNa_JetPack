package com.example.dina_compose

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private val Context.dataStore by preferencesDataStore("bearer_token_datastore")


class UserPreference(private val context : Context)
{
  private val userDataStore = context.dataStore


  suspend fun getBearerToken(): String? {
    val preferences = userDataStore.data.first()
    return preferences[PreferenceKeys.BEARER_TOKEN]
  }

  suspend fun getUid(): String? {
    val preferences = userDataStore.data.first()
    return preferences[PreferenceKeys.KEY_UID]
  }

  suspend fun saveBearerToken(token: String?, uid: String?) {
    userDataStore.edit { preferences ->
      preferences[PreferenceKeys.KEY_UID] = uid ?: ""
      preferences[PreferenceKeys.BEARER_TOKEN] = token ?: ""
    }
  }



  object PreferenceKeys {
    val KEY_UID = stringPreferencesKey("uid")
    val BEARER_TOKEN = stringPreferencesKey("bearer_token")
  }

}