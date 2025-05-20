package com.satc.integrador_ai.data.model.request

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