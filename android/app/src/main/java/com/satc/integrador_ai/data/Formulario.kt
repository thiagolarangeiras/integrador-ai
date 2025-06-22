package com.satc.integrador_ai.data

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import java.time.DayOfWeek
import javax.inject.Inject
import javax.inject.Singleton

data class Formulario (
    val idioma: String? = null,
    val tipoExercicio: List<String> = emptyList(),
    val temas: List<String> = emptyList(),
    val dificuldade: String? = null,
    val nivel: String? = null,
    val diaSemana: List<Int> = emptyList(),
    val tempoMinutos: Int? = null
)

data class PreferenceRequest (
    val idUsuario: Int? = null,
    val idioma: String? = null,
    val tipoExercicio: List<String> = emptyList(),
    val temas: List<String> = emptyList(),
    val dificuldade: String? = null,
    val nivel: String? = null,
    val diaSemana: List<Int> = emptyList(),
    val tempoMinutos: Int? = null
)

data class PreferenceResponse (
    val id: Int? = null,
    val idUsuario: Int? = null,
    val idioma: String? = null,
    val tipoExercicio: List<String>? = null,
    val temas: List<String>? = null,
    val dificuldade: String? = null,
    val nivel: String? = null,
    val diaSemana: List<DayOfWeek>? = null,
    val tempoMinutos: Int? = null
)

interface FormPreferenceService {
    @POST("preferencia")
    suspend fun createPreference(@Body request: PreferenceRequest): PreferenceResponse

    @GET("preferencia")
    suspend fun getPreference(): PreferenceResponse
}

@Singleton
class PreferenceRepository @Inject constructor(
    private val formPreferenceService: FormPreferenceService
) {
    suspend fun createPreference(preferenceRequest: PreferenceRequest): PreferenceResponse {
        return formPreferenceService.createPreference(preferenceRequest)
    }

    suspend fun getPreference(): PreferenceResponse {
        return formPreferenceService.getPreference()
    }
}