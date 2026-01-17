package com.example.pam_project_medicat_009.viewmodel

import android.content.Context
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pam_project_medicat_009.datastore.UserPreferences
import kotlinx.coroutines.launch

class MainViewModel(context: Context) : ViewModel() {

    private val prefs = UserPreferences(context)

    var isLoggedIn by mutableStateOf(false)
        private set

    var role by mutableStateOf("")
        private set

    var userId by mutableStateOf(0)
        private set

    init {
        viewModelScope.launch {
            val token = prefs.getToken()
            val roleSaved = prefs.getRole()
            val id = prefs.getUserId()

            if (!token.isNullOrEmpty()) {
                isLoggedIn = true
                role = roleSaved ?: ""
                userId = id
            }
        }
    }
}
