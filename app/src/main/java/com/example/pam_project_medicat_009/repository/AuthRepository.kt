package com.example.pam_project_medicat_009.repository

import com.example.pam_project_medicat_009.apiservice.RetrofitClient
import com.example.pam_project_medicat_009.modeldata.LoginRequest

class AuthRepository {
    suspend fun login(email: String, password: String) =
        RetrofitClient.api.login(
            LoginRequest(email, password)
        )
}