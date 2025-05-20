package com.satc.integrador_ai.data.api

import com.satc.integrador_ai.data.model.request.PreferenceRequest
import com.satc.integrador_ai.data.model.response.PreferenceResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface FormPreferenceService {
    @POST("preferencia")
    suspend fun createPreference(@Body request: PreferenceRequest): PreferenceResponse
}