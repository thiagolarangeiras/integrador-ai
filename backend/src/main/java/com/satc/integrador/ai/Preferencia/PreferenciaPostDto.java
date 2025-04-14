package com.satc.integrador.ai.Preferencia;

import java.time.DayOfWeek;
import java.util.List;

public record PreferenciaPostDto(
        Integer idUsuario,
        String idioma,
        List<String> tipoExercicio,
        List<String> temas,
        String dificuldade,
        String nivel,
        List<DayOfWeek> diaSemana,
        Integer tempoMinutos
) { }