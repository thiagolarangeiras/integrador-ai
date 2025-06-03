package com.satc.integrador.ai.studyplan.dto;

import com.satc.integrador.ai.enums.TipoExercicios;
import com.satc.integrador.ai.exercicios.ExercicioGramaticaComplementarGetDto;
import com.satc.integrador.ai.exercicios.ExercicioGramaticaOrdemGetDto;
import com.satc.integrador.ai.exercicios.ExercicioVocabualrioParesGetDto;
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
    public List<ExercicioGramaticaComplementarGetDto> exerGramaCompl;
    public List<ExercicioGramaticaOrdemGetDto> exerGramaOrdem;
    public List<ExercicioVocabualrioParesGetDto> exerVocPares;
}
