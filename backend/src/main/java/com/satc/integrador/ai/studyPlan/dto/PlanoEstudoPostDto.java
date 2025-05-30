package com.satc.integrador.ai.studyPlan.dto;

import com.satc.integrador.ai.enums.TipoExercicios;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class PlanoEstudoPostDto {
    public Integer idUsuario;
    public Integer idPreferencia; //preferencias que moldaram esse plano

    public Integer qtExerciciosDia;
    public List<TipoExercicios> tiposExerciciosContidos;
}