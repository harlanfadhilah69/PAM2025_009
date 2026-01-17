package com.example.pam_project_medicat_009.repository

import com.example.pam_project_medicat_009.apiservice.RetrofitClient

class PasienRepository {
    suspend fun getPasien(id: Int) =
        RetrofitClient.api.getPasienById(id)
}
