package com.satc.integrador.ai.exercicios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExercicioGramaticaOrdemGetDto {
    private Integer id;
    private Integer idOrdemExercicio;
    private Integer idPlanoEstudo;

    private String fraseCompleta;
    private List<String> ordemCorreta;
    private List<String> ordemAleatoria;
}