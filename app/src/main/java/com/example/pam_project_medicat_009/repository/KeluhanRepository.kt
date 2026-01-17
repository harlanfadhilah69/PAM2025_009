package com.example.pam_project_medicat_009.repository

import com.example.pam_project_medicat_009.apiservice.RetrofitClient
import com.example.pam_project_medicat_009.modeldata.Keluhan

object KeluhanRepository {

    suspend fun getDetailKeluhan(id: Int): Keluhan {
        return RetrofitClient.api.getDetailKeluhan(id)
    }

}
