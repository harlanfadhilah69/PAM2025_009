package com.example.pam_project_medicat_009.modeldata.dokter

import com.google.gson.annotations.SerializedName

data class Dokter(
    @SerializedName("dokter_id")
    val dokterId: Int,

    @SerializedName("nama")
    val nama: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("spesialisasi")
    val spesialisasi: String,

    @SerializedName("nomor_izin_praktek")
    val nomorIzinPraktek: String
)
