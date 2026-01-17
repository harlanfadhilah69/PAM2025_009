package com.example.pam_project_medicat_009.apiservice

import com.example.pam_project_medicat_009.modeldata.BaseResponse
import com.example.pam_project_medicat_009.modeldata.Jadwal
import com.example.pam_project_medicat_009.modeldata.JadwalRequest
import com.example.pam_project_medicat_009.modeldata.Keluhan
import com.example.pam_project_medicat_009.modeldata.KeluhanDetailResponse
import com.example.pam_project_medicat_009.modeldata.KeluhanRequest
import com.example.pam_project_medicat_009.modeldata.LoginRequest
import com.example.pam_project_medicat_009.modeldata.LoginResponse
import com.example.pam_project_medicat_009.modeldata.Pasien
import com.example.pam_project_medicat_009.modeldata.RegisterDokterRequest
import com.example.pam_project_medicat_009.modeldata.RegisterRequest
import com.example.pam_project_medicat_009.modeldata.ResponsRequest
import com.example.pam_project_medicat_009.modeldata.UpdateKeluhanRequest
import retrofit2.Response
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import com.example.pam_project_medicat_009.modeldata.dokter.Dokter


interface ApiService {

    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse

    @POST("auth/register/pasien")
    suspend fun registerPasien(
        @Body request: RegisterRequest
    )

    @POST("auth/register/dokter")
    suspend fun registerDokter(
        @Body request: RegisterDokterRequest
    )


    @GET("pasien/{id}")
    suspend fun getPasienById(
        @Path("id") id: Int
    ): Pasien


    @GET("keluhan/pasien/{id}")
    suspend fun getKeluhanPasien(
        @Path("id") pasienId: Int
    ): List<Keluhan>

    @GET("keluhan/pending")
    suspend fun getKeluhanMasuk(): List<Keluhan>

    @PUT("keluhan/{id}")
    suspend fun updateKeluhan(
        @Path("id") id: Int,
        @Body body: UpdateKeluhanRequest
    )

    @DELETE("keluhan/{id}")
    suspend fun deleteKeluhan(@Path("id") id: Int)

    @POST("keluhan")
    suspend fun createKeluhan(
        @Body request: KeluhanRequest
    )

    @POST("respons")
    suspend fun kirimRespons(
        @Body request: ResponsRequest
    )



    @GET("keluhan/detail/{id}")
    suspend fun getDetailKeluhan(
        @Path("id") id: Int
    ): Keluhan

    @POST("jadwal")
    suspend fun tambahJadwal(
        @Body request: JadwalRequest
    )

    @GET("jadwal/dokter/{id}")
    suspend fun getJadwalDokter(
        @Path("id") dokterId: Int
    ): List<Jadwal>

    @PUT("jadwal/{id}")
    suspend fun updateJadwal(
        @Path("id") id: Int,
        @Body request: JadwalRequest
    ): ResponseBody

    @DELETE("jadwal/{id}")
    suspend fun deleteJadwal(
        @Path("id") id: Int
    ): Response<ResponseBody>

    @GET("api/dokter/profil/{id}")
    suspend fun getProfilDokter(
        @Path("id") id: Int
    ): Dokter


}

