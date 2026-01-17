package com.example.pam_project_medicat_009.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pam_project_medicat_009.apiservice.RetrofitClient
import com.example.pam_project_medicat_009.modeldata.Pasien
import kotlinx.coroutines.launch

class PasienViewModel : ViewModel() {

    var pasien by mutableStateOf<Pasien?>(null)
        private set

    fun loadPasien(pasienId: Int) {
        viewModelScope.launch {
            try {
                pasien = RetrofitClient.api.getPasienById(pasienId)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
