package com.satc.integrador.ai.studyPlan.dto;

import com.satc.integrador.ai.enums.TipoExercicios;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class PlanoEstudoGetDto {
    Integer id;
    Integer idUsuario;
    Integer idPreferencia; //preferencias que moldaram esse plano

    Integer qtExerciciosDia;
    List<TipoExercicios> tiposExerciciosContidos;
}
