package com.satc.integrador.ai.preference.dto;

import java.time.DayOfWeek;
import java.util.List;

public record PreferenciaGetDto(
        Integer id,
        Integer idUsuario,
        String idioma,
        List<String>tipoExercicio,
        List<String> temas,
        String dificuldade,
        String nivel,
        List<DayOfWeek> diaSemana,
        Integer tempoMinutos,
        Boolean ativo
) { }