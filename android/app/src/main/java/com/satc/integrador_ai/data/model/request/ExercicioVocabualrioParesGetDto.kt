package com.satc.integrador_ai.data.model.request

import com.satc.integrador_ai.storage.TipoExercicio

class ExercicioVocabualrioParesGetDto (
    val id: Int ?= null,
    val idOrdemExercicio: Int ?= null,
    val idPlanoEstudo: Int ?= null,
    val paresEsquerda: MutableList<String> ?= null,
    val paresDireita: MutableList<String> ?= null
) : TipoExercicio {
    override fun getTipoExercicio(): String {
        return "VocabularioPares"
    }
}