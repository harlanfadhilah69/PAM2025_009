package com.example.pam_project_medicat_009.modeldata

data class ResponsRequest(
    val keluhan_id: Int,
    val dokter_id: Int,
    val diagnosis_sementara: String,
    val anjuran_polahidup: String,
    val rekomendasi_rujukan: String
)

data class Respons(
    val diagnosis_sementara: String,
    val anjuran_polahidup: String,
    val rekomendasi_rujukan: String,
    val dokter: Dokter
)


