package com.satc.integrador_ai.data.repository

import com.satc.integrador_ai.data.api.PlanoEstudoService
import com.satc.integrador_ai.data.model.request.PlanoEstudoResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlanoEstudoRepository @Inject constructor(
    private val planoEstudoService: PlanoEstudoService
){

    suspend fun generateNewPlan(): PlanoEstudoResponse {
        return planoEstudoService.generateNewPlan()
    }

    suspend fun getToday(): PlanoEstudoResponse {
        return planoEstudoService.getToday()
    }

    suspend fun getById(id: Int): PlanoEstudoResponse {
        return planoEstudoService.getById(id)
    }
}