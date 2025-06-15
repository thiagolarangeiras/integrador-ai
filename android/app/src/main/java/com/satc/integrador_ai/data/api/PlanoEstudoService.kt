package com.satc.integrador_ai.data.api

import com.satc.integrador_ai.data.model.request.PlanoEstudoResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PlanoEstudoService {

    @POST("plano-estudo/generate-new-plan")
    suspend fun generateNewPlan(): PlanoEstudoResponse

    @GET("plano-estudo/today")
    suspend fun getToday(): PlanoEstudoResponse

    @GET("plano-estudo/{id}")
    suspend fun getById(@Path("id") id: Int): PlanoEstudoResponse
}