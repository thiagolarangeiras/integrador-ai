package com.satc.integrador_ai.data.model.request

import com.satc.integrador_ai.storage.TipoExercicio

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