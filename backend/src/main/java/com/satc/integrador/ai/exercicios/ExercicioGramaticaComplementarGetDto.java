package com.satc.integrador.ai.exercicios;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExercicioGramaticaComplementarGetDto {
    private Integer id;
    private Integer idOrdemExercicio;
    private Integer idPlanoEstudo;

    private String fraseCompleta;
    private String fraseIncompleta;
    private String opcaoCorreta;
    private List<String> opcaoIncorreta;
}