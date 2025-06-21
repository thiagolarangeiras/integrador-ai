package com.satc.integrador_ai.storage

import com.satc.integrador_ai.data.model.request.ExercicioGramaticaCompletarGetDto
import com.satc.integrador_ai.data.model.request.ExercicioGramaticaOrdemGetDto
import com.satc.integrador_ai.data.model.request.ExercicioVocabualrioParesGetDto
import java.time.LocalDate

class Exercicios (
    var id: Int? = null,
    var nome: String? = null,
    var data: LocalDate? = null,
    var qtExerciciosDia: Int? = null,
    var exerGramaCompl: MutableList<ExercicioGramaticaCompletarGetDto>,
    var exerGramaOrdem: MutableList<ExercicioGramaticaOrdemGetDto>,
    var exerVocPares: MutableList<ExercicioVocabualrioParesGetDto>
)