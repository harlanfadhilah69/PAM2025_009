package com.example.pam_project_medicat_009.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pam_project_medicat_009.apiservice.RetrofitClient
import com.example.pam_project_medicat_009.modeldata.Keluhan
import com.example.pam_project_medicat_009.repository.KeluhanRepository
import kotlinx.coroutines.launch

class KeluhanDetailPasienViewModel : ViewModel() {

    var isLoading by mutableStateOf(false)
        private set

    var keluhan by mutableStateOf<Keluhan?>(null)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun loadDetail(keluhanId: Int) {
        viewModelScope.launch {
            isLoading = true
            try {
                keluhan = RetrofitClient.api.getDetailKeluhan(keluhanId)
            } catch (e: Exception) {
                errorMessage = e.message
                keluhan = null
            } finally {
                isLoading = false
            }
        }
    }
}

