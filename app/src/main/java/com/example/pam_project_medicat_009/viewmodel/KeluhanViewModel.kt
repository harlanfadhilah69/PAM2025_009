package com.example.pam_project_medicat_009.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pam_project_medicat_009.apiservice.RetrofitClient
import com.example.pam_project_medicat_009.modeldata.Keluhan
import com.example.pam_project_medicat_009.modeldata.KeluhanRequest
import com.example.pam_project_medicat_009.modeldata.UpdateKeluhanRequest
import kotlinx.coroutines.launch

class KeluhanViewModel : ViewModel() {

    // ======================
    // STATE UMUM
    // ======================
    var isLoading by mutableStateOf(false)
        private set

    var success by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    // ======================
    // LIST KELUHAN
    // ======================
    var keluhanList by mutableStateOf<List<Keluhan>>(emptyList())
        private set

    fun loadKeluhan(pasienId: Int) {
        viewModelScope.launch {
            isLoading = true
            try {
                keluhanList = RetrofitClient.api.getKeluhanPasien(pasienId)
            } catch (e: Exception) {
                errorMessage = e.message
            } finally {
                isLoading = false
            }
        }
    }

    // ======================
    // DETAIL KELUHAN
    // ======================
    var selectedKeluhan by mutableStateOf<Keluhan?>(null)
        private set

    fun loadDetailKeluhan(id: Int) {
        viewModelScope.launch {
            isLoading = true
            try {
                selectedKeluhan = RetrofitClient.api.getDetailKeluhan(id)
            } catch (e: Exception) {
                errorMessage = e.message
                selectedKeluhan = null
            } finally {
                isLoading = false
            }
        }
    }


    // ======================
    // TAMBAH KELUHAN
    // ======================
    fun submitKeluhan(
        pasienId: Int,
        gejala: String,
        deskripsi: String
    ) {
        viewModelScope.launch {
            isLoading = true
            success = false
            try {
                RetrofitClient.api.createKeluhan(
                    KeluhanRequest(
                        pasien_id = pasienId,
                        gejala_utama = gejala,
                        deskripsi = deskripsi
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

    // ======================
    // UPDATE
    // ======================
    fun updateKeluhan(
        keluhanId: Int,
        gejala: String,
        deskripsi: String,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            isLoading = true
            try {
                RetrofitClient.api.updateKeluhan(
                    keluhanId,
                    UpdateKeluhanRequest(
                        gejala_utama = gejala,
                        deskripsi = deskripsi
                    )
                )
                onSuccess()
            } catch (e: Exception) {
                errorMessage = e.message
            } finally {
                isLoading = false
            }
        }
    }

    // ======================
    // DELETE
    // ======================
    fun deleteKeluhan(
        keluhanId: Int,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            isLoading = true
            try {
                RetrofitClient.api.deleteKeluhan(keluhanId)
                onSuccess()
            } catch (e: Exception) {
                errorMessage = e.message
            } finally {
                isLoading = false
            }
        }
    }
}

