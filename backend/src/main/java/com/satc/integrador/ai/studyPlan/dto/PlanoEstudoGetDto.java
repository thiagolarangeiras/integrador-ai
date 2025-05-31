package com.satc.integrador.ai.studyPlan.dto;

import com.satc.integrador.ai.enums.TipoExercicios;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class PlanoEstudoGetDto {
    public Integer id;
    public Integer idUsuario;
    public Integer idPreferencia;
    public Integer qtExerciciosDia;
    public List<TipoExercicios> tiposExerciciosContidos;
    public ExercicioGramaticaComplementarGetDto exercicioGramaticaComplementarGetDto;
    public ExercicioGramaticaOrdemGetDto exercicioGramaticaOrdemGetDto;
    public ExercicioVocabualrioParesGetDto exercicioVocabualrioParesGetDto;
}
