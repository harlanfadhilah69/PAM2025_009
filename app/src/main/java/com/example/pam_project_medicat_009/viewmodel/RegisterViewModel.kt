package com.example.pam_project_medicat_009.viewmodel

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pam_project_medicat_009.apiservice.RetrofitClient
import com.example.pam_project_medicat_009.modeldata.RegisterDokterRequest
import com.example.pam_project_medicat_009.modeldata.RegisterRequest
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var registerSuccess by mutableStateOf(false)
        private set

    fun registerPasien(
        nama: String,
        email: String,
        password: String,
        noTelp: String
    ) {
        viewModelScope.launch {
            try {
                RetrofitClient.api.registerPasien(
                    RegisterRequest(
                        nama = nama,
                        email = email,
                        password = password,
                        no_telp = noTelp
                    )
                )
                registerSuccess = true
            } catch (e: Exception) {
                errorMessage = e.message
            }
        }
    }

    fun registerDokter(
        nama: String,
        email: String,
        password: String,
        spesialisasi: String,
        nomorIzin: String
    ) {
        viewModelScope.launch {
            try {
                RetrofitClient.api.registerDokter(
                    RegisterDokterRequest(
                        nama, email, password, spesialisasi, nomorIzin
                    )
                )
                registerSuccess = true
            } catch (e: Exception) {
                errorMessage = e.message
            }
        }
    }
}



