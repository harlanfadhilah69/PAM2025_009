package com.example.pam_project_medicat_009.datastore

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private val Context.dataStore by preferencesDataStore(name = "user_pref")

class UserPreferences(private val context: Context) {

    companion object {
        val TOKEN = stringPreferencesKey("token")
        val ROLE = stringPreferencesKey("role")
        val USER_ID = intPreferencesKey("user_id")
    }

    suspend fun saveUser(token: String, role: String, userId: Int) {
        context.dataStore.edit { prefs ->
            prefs[TOKEN] = token
            prefs[ROLE] = role
            prefs[USER_ID] = userId
        }
    }

    suspend fun getToken(): String? =
        context.dataStore.data.first()[TOKEN]

    suspend fun getRole(): String? =
        context.dataStore.data.first()[ROLE]

    suspend fun getUserId(): Int =
        context.dataStore.data.first()[USER_ID] ?: 0

    suspend fun logout() {
        context.dataStore.edit {
            it.clear()
        }
    }
}
