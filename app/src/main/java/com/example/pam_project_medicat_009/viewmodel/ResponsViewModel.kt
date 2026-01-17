package com.example.pam_project_medicat_009.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pam_project_medicat_009.apiservice.RetrofitClient
import com.example.pam_project_medicat_009.modeldata.ResponsRequest
import kotlinx.coroutines.launch

class ResponsViewModel : ViewModel() {

    var isLoading by mutableStateOf(false)
    var success by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    fun kirimRespons(
        keluhanId: Int,
        dokterId: Int,
        diagnosis: String,
        anjuran: String,
        rujukan: String
    ) {
        viewModelScope.launch {
            isLoading = true
            try {
                RetrofitClient.api.kirimRespons(
                    ResponsRequest(
                        keluhan_id = keluhanId,
                        dokter_id = dokterId,
                        diagnosis_sementara = diagnosis,
                        anjuran_polahidup = anjuran,
                        rekomendasi_rujukan = rujukan
                    )
                )
                success = true
            } catch (e: Exception) {
                errorMessage = e.message
            } finally {
                isLoading = false
            }
        }
    }
}
