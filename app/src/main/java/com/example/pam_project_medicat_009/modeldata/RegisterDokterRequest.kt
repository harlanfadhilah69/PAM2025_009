package com.example.pam_project_medicat_009.modeldata

data class RegisterDokterRequest(
    val nama: String,
    val email: String,
    val password: String,
    val spesialisasi: String,
    val nomor_izin_praktek: String
)
