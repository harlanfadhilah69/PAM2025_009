package com.example.pam_project_medicat_009.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pam_project_medicat_009.apiservice.RetrofitClient
import com.example.pam_project_medicat_009.datastore.UserPreferences
import com.example.pam_project_medicat_009.modeldata.LoginRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    var loginSuccess by mutableStateOf(false)
        private set

    var userId by mutableStateOf(0)
        private set

    var role by mutableStateOf("")
        private set

    var nama by mutableStateOf("")      // ✅ WAJIB ADA
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.api.login(
                    LoginRequest(email, password)
                )

                userId = response.id
                role = response.role
                loginSuccess = true
                errorMessage = null

            } catch (e: Exception) {
                errorMessage = "Email atau password salah"
                loginSuccess = false
            }
        }
    }
}




