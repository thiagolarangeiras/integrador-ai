package com.satc.integrador_ai.storage

import com.satc.integrador_ai.data.model.request.ExercicioGramaticaCompletarGetDto
import com.satc.integrador_ai.data.model.request.ExercicioGramaticaOrdemGetDto
import com.satc.integrador_ai.data.model.request.ExercicioVocabualrioParesGetDto

class Exercicios (
    var id: Int ?= null,
    var qtExerciciosDia: Int ?= null,
    var exerGramaCompl: MutableList<ExercicioGramaticaCompletarGetDto>,
    var exerGramaOrdem: MutableList<ExercicioGramaticaOrdemGetDto>,
    var exerVocPares: MutableList<ExercicioVocabualrioParesGetDto>
)