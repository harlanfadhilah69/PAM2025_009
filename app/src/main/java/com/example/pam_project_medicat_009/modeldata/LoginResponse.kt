package com.example.pam_project_medicat_009.modeldata

data class LoginResponse(
    val message: String,
    val token: String,
    val role: String,
    val id: Int,
    val nama: String
)