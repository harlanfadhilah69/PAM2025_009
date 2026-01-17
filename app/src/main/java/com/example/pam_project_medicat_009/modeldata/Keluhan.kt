package com.example.pam_project_medicat_009.modeldata

import com.google.gson.annotations.SerializedName

data class Keluhan(

    @SerializedName("keluhan_id")
    val idKeluhan: Int,

    @SerializedName("pasien_id")
    val pasienId: Int,

    @SerializedName("gejala_utama")
    val gejalaUtama: String,

    val deskripsi: String?,
    val status: String,

    val respons: Respons?
)

