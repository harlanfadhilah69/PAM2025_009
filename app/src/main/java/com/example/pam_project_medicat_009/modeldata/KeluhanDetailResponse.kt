package com.example.pam_project_medicat_009.modeldata

import com.google.gson.annotations.SerializedName

data class KeluhanDetailResponse(
    @SerializedName("keluhan_id")
    val idKeluhan: Int,

    @SerializedName("gejala_utama")
    val gejalaUtama: String,

    val deskripsi: String?,
    val status: String,
    val respons: ResponsDokter?
)

data class ResponsDokter(
    @SerializedName("diagnosis_sementara")
    val diagnosisSementara: String,

    @SerializedName("anjuran_polahidup")
    val anjuranPolaHidup: String,

    @SerializedName("rekomendasi_rujukan")
    val rekomendasiRujukan: String,

    val dokter: Dokter?
)

data class Dokter(
    @SerializedName("dokter_id")
    val dokterId: Int,

    val nama: String
)


data class ResponsDetail(
    val diagnosis_sementara: String,
    val anjuran_polahidup: String,
    val rekomendasi_rujukan: String,
    val dokter: DokterMini
)

data class DokterMini(
    val nama: String
)
