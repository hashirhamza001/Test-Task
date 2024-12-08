package com.syeda.myapplication.repo
import retrofit2.http.GET

import com.syeda.myapplication.model.Medicine
import retrofit2.http.Headers


interface MedicineApi {
    @GET("v3/a0d4625c-8c44-4a01-ace8-17850cebd316")
    @Headers("Content-Type: application/json")
    suspend fun getMedicines(): List<Medicine>
}