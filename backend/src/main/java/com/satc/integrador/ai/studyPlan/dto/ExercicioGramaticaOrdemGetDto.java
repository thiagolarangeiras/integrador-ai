package com.satc.integrador.ai.studyPlan.dto;

import java.util.List;

public class ExercicioGramaticaOrdemGetDto {
    private Integer id;
    private Integer idOrdemExercicio;
    private Integer idPlanoEstudo;
    private String fraseCompleta;
    private List<String> ordemCorreta;
    private List<String> ordemAleatoria;
}