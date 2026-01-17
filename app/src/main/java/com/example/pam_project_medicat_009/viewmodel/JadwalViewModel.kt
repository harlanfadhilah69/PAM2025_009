package com.example.pam_project_medicat_009.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pam_project_medicat_009.apiservice.RetrofitClient
import com.example.pam_project_medicat_009.modeldata.Jadwal
import com.example.pam_project_medicat_009.modeldata.JadwalRequest
import kotlinx.coroutines.launch

class JadwalViewModel : ViewModel() {

    var jadwalList by mutableStateOf<List<Jadwal>>(emptyList())
        private set

    fun loadJadwalDokter(dokterId: Int) {
        viewModelScope.launch {
            try {
                jadwalList = RetrofitClient.api.getJadwalDokter(dokterId)
            } catch (e: Exception) {
                jadwalList = emptyList()
            }
        }
    }

    fun tambahJadwal(
        dokterId: Int,
        hari: String,
        jam: String,
        lokasi: String,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            try {
                RetrofitClient.api.tambahJadwal(
                    JadwalRequest(dokterId, hari, jam, lokasi)
                )
                onSuccess()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateJadwal(
        jadwalId: Int,
        dokterId: Int,
        hari: String,
        jam: String,
        lokasi: String,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            try {
                RetrofitClient.api.updateJadwal(
                    jadwalId,
                    JadwalRequest(dokterId, hari, jam, lokasi)
                )
                onSuccess()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deleteJadwal(
        jadwalId: Int,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            try {
                println("DELETE JADWAL ID = $jadwalId")

                val response = RetrofitClient.api.deleteJadwal(jadwalId)

                println("RESPONSE CODE = ${response.code()}")

                if (response.isSuccessful) {
                    println("DELETE SUCCESS")
                    onSuccess()
                } else {
                    println("DELETE FAILED: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
