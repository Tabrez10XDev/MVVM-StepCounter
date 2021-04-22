package com.lj.bwow.api


import com.lj.bwow.data.HealthResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HealthAPI {

    @GET("/b/60816ce39a9aa933335504a8")
    suspend fun getHealthData(): Response<HealthResponse>

}