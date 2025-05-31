package com.satc.integrador.ai.studyPlan.dto;

import java.util.List;

public class ExercicioGramaticaComplementarGetDto {
    private Integer id;
    private Integer idOrdemExercicio;
    private Integer idPlanoEstudo;
    private String fraseCompleta;
    private String fraseIncompleta;
    private String opcaoCorreta;
    private List<String> opcaoIncorreta;
}