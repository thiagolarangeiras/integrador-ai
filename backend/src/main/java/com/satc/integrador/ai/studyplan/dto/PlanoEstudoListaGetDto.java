package com.satc.integrador.ai.studyplan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanoEstudoListaGetDto {
    public Integer porcentagemCompleta;
    public List<PlanoEstudoGetDto> planos;
}