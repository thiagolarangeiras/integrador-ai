package com.satc.integrador.ai.studyplan.dto;

import com.satc.integrador.ai.enums.TipoExercicios;
import com.satc.integrador.ai.exercicios.dto.ExercicioGramaticaComplementarGetDto;
import com.satc.integrador.ai.exercicios.dto.ExercicioGramaticaOrdemGetDto;
import com.satc.integrador.ai.exercicios.dto.ExercicioVocabualrioParesGetDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanoEstudoGetDto {
    public Integer id;
    public Integer idUsuario;
    public Integer idPreferencia;
    public String nome;
    public LocalDate data;
    public Boolean ativo;
    public Boolean finalizado;
    public Integer qtExercicios;
    public Integer qtExerciciosFinalizados;
    public List<TipoExercicios> tiposExerciciosContidos;
    public List<ExercicioGramaticaComplementarGetDto> exerGramaCompl;
    public List<ExercicioGramaticaOrdemGetDto> exerGramaOrdem;
    public List<ExercicioVocabualrioParesGetDto> exerVocPares;
}
