package com.example.pam_project_medicat_009.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pam_project_medicat_009.apiservice.RetrofitClient
import com.example.pam_project_medicat_009.modeldata.Keluhan
import com.example.pam_project_medicat_009.modeldata.dokter.Dokter
import kotlinx.coroutines.launch

class DokterViewModel : ViewModel() {

    var dokter = mutableStateOf<Dokter?>(null)
        private set

    var keluhanList = mutableStateOf<List<Keluhan>>(emptyList())
        private set

    fun loadProfilDokter(dokterId: Int) {
        viewModelScope.launch {
            try {
                dokter.value = RetrofitClient.api.getProfilDokter(dokterId)
            } catch (e: Exception) {
                e.printStackTrace()
                dokter.value = null
            }
        }
    }


    fun loadKeluhanMasuk() {
        viewModelScope.launch {
            keluhanList.value = RetrofitClient.api.getKeluhanMasuk()
        }
    }
}
