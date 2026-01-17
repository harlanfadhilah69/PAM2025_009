package com.example.pam_project_medicat_009.modeldata

data class KeluhanRequest(
    val pasien_id: Int,
    val gejala_utama: String,
    val deskripsi: String
)
data class UpdateKeluhanRequest(
    val gejala_utama: String,
    val deskripsi: String
)