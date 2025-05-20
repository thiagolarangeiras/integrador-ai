package com.satc.integrador_ai.data.model.response

import java.time.DayOfWeek

data class PreferenceResponse (
    val id: Int,
    val idUsuario: Int,
    val idioma: String,
    val tipoExercicio: List<String>,
    val temas: List<String>,
    val dificuldade: String,
    val nivel: String,
    val diaSemana: List<DayOfWeek>,
    val tempoMinutos: Int
)