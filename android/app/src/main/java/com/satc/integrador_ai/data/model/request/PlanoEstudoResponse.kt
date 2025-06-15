package com.satc.integrador_ai.data.model.request

class PlanoEstudoResponse (
    val id: Int ?= null,
    val idUsuario: Int ?= null,
    val idPreferencia: Int ?= null,
    val qtExerciciosDia: Int ?= null,
    val tiposExerciciosContidos: MutableList<String> ?= null,
    val exerGramaCompl: MutableList<ExercicioGramaticaCompletarGetDto> ?= null,
    val exerGramaOrdem: MutableList<ExercicioGramaticaOrdemGetDto> ?= null,
    val exerVocPares: MutableList<ExercicioVocabualrioParesGetDto> ?= null
)