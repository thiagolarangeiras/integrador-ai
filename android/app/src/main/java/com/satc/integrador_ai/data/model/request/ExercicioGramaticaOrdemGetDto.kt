package com.satc.integrador_ai.data.model.request

import com.satc.integrador_ai.storage.TipoExercicio

class ExercicioGramaticaOrdemGetDto (
    val id: Int ?= null,
    val idOrdemExercicio: Int ?= null,
    val idPlanoEstudo: Int ?= null,
    val fraseCompleta: String ?= null,
    val opcaoCorreta: MutableList<String> ?= null,
    val ordemAleatoria: MutableList<String> ?= null
) : TipoExercicio {
    override fun getTipoExercicio(): String {
        return "GramaticaOrdem"
    }
}