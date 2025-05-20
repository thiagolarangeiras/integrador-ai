package com.satc.integrador_ai.data.repository

import com.satc.integrador_ai.data.api.FormPreferenceService
import com.satc.integrador_ai.data.model.request.PreferenceRequest
import com.satc.integrador_ai.data.model.response.PreferenceResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferenceRepository @Inject constructor(
    private val formPreferenceService: FormPreferenceService
) {
    suspend fun createPreference(preferenceRequest: PreferenceRequest): PreferenceResponse {
        return formPreferenceService.createPreference(preferenceRequest)
    }
}