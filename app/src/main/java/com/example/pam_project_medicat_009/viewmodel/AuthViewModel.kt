package com.example.pam_project_medicat_009.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pam_project_medicat_009.datastore.UserPreferences
import kotlinx.coroutines.launch

class AuthViewModel(
    private val userPreferences: UserPreferences
) : ViewModel() {

    fun logout() {
        viewModelScope.launch {
            userPreferences.logout()
        }
    }
}
