package com.example.pam_project_medicat_009.modeldata

import com.google.gson.annotations.SerializedName

data class Jadwal(
    @SerializedName("jadwal_id")
    val id: Int,

    @SerializedName("dokter_id")
    val dokterId: Int,

    val hari: String,
    val jam_mulai: String,
    val lokasi_rs: String
)


data class JadwalRequest(
    @SerializedName("dokter_id")
    val dokterId: Int,

    @SerializedName("hari")
    val hari: String,

    @SerializedName("jam_mulai")
    val jamMulai: String,

    @SerializedName("lokasi_rs")
    val lokasiRs: String
)

