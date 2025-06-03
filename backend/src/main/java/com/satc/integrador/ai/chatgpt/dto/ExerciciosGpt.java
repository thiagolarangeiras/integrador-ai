package com.satc.integrador.ai.chatgpt.dto;

import com.satc.integrador.ai.enums.TipoExercicios;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciciosGpt {
    public TipoExercicios tipo;
    public Object dados;
}