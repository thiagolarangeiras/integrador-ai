package com.satc.integrador_ai.data

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

interface TipoExercicio {
    fun getTipoExercicio(): String
}

class ExercicioGramaticaCompletarGetDto (
    val id: Int ?= null,
    val idOrdemExercicio: Int ?= null,
    val idPlanoEstudo: Int ?= null,
    val fraseCompleta: String ?= null,
    val fraseIncompleta: String ?= null,
    val opcaoCorreta: String ?= null,
    val opcaoIncorreta: MutableList<String> ?= null
) : TipoExercicio {
    override fun getTipoExercicio(): String {
        return "GramaticaCompletar"
    }
}

class ExercicioGramaticaOrdemGetDto (
    val id: Int ?= null,
    val idOrdemExercicio: Int ?= null,
    val idPlanoEstudo: Int ?= null,
    val fraseCompleta: String ?= null,
    val ordemCorreta: MutableList<String> ?= null,
    val ordemAleatoria: MutableList<String> ?= null
) : TipoExercicio {
    override fun getTipoExercicio(): String {
        return "GramaticaOrdem"
    }
}

class ExercicioVocabualrioParesGetDto (
    val id: Int ?= null,
    val idOrdemExercicio: Int ?= null,
    val idPlanoEstudo: Int ?= null,
    val paresEsquerda: MutableList<String> ?= null,
    val paresDireita: MutableList<String> ?= null,
    var respostaCorreta: HashMap<String, String> ?= null
) : TipoExercicio {
    override fun getTipoExercicio(): String {
        return "VocabularioPares"
    }
}

class Exercicios (
    var id: Int? = null,
    var nome: String? = null,
    var data: LocalDate? = null,
    var qtExercicios: Int? = null,
    var qtExerciciosFinalizados: Int? = null,
    var exerGramaCompl: MutableList<ExercicioGramaticaCompletarGetDto>? = null,
    var exerGramaOrdem: MutableList<ExercicioGramaticaOrdemGetDto>? = null,
    var exerVocPares: MutableList<ExercicioVocabualrioParesGetDto>? = null
)

class PlanoEstudoLista (
    var porcentagemCompleta: Int? = null,
    var planos: MutableList<Exercicios>? = null,
)

class PlanoEstudoResponse (
    val id: Int? = null,
    val idUsuario: Int? = null,
    val idPreferencia: Int? = null,
    var nome: String? = null,
    var data: LocalDate? = null,
    val qtExercicios: Int? = null,
    val qtExerciciosFinalizados: Int? = null,
    val tiposExerciciosContidos: MutableList<String>? = null,
    val exerGramaCompl: MutableList<ExercicioGramaticaCompletarGetDto>? = null,
    val exerGramaOrdem: MutableList<ExercicioGramaticaOrdemGetDto>? = null,
    val exerVocPares: MutableList<ExercicioVocabualrioParesGetDto>? = null
)

interface PlanoEstudoService {
    @POST("plano-estudo/generate-new-plan")
    suspend fun generateNewPlan(): PlanoEstudoResponse

    @GET("plano-estudo/today")
    suspend fun getToday(): PlanoEstudoResponse

    @GET("plano-estudo/{id}")
    suspend fun getById(@Path("id") id: Int): PlanoEstudoResponse

    @GET("plano-estudo")
    suspend fun getAll(@Query("page") page: Int, @Query("count") count: Int,): PlanoEstudoLista

    @POST("plano-estudo/{id}/finalizar-exercicio/{id-exercicio}")
    suspend fun finalizarExercicio(@Path("id") id: Int, @Path("id-exercicio") idExercicio: Int): Boolean

    @POST("plano-estudo/{id}/finalizar")
    suspend fun finalizarPlano(@Path("id") id: Int): Boolean
}

@Singleton
class PlanoEstudoRepository @Inject constructor(
    private val planoEstudoService: PlanoEstudoService
) {
    suspend fun generateNewPlan(): PlanoEstudoResponse {
        return planoEstudoService.generateNewPlan()
    }

    suspend fun getToday(): PlanoEstudoResponse {
        return planoEstudoService.getToday()
    }

    suspend fun getById(id: Int): PlanoEstudoResponse {
        return planoEstudoService.getById(id)
    }

    suspend fun getAll(page: Int, count: Int): PlanoEstudoLista {
        return planoEstudoService.getAll(page, count)
    }

    suspend fun finalizarExercicio(id: Int, idExercicio: Int): Boolean {
        return planoEstudoService.finalizarExercicio(id, idExercicio)
    }

    suspend fun getAll(id: Int): Boolean {
        return planoEstudoService.finalizarPlano(id)
    }
}