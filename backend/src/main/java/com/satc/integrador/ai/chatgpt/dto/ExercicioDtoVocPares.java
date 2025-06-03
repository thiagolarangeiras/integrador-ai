package com.satc.integrador.ai.chatgpt.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExercicioDtoVocPares implements IExerciciosGpt {
    @JsonProperty("pares_esquerda")
    public List<String> paresEsquerda;

    @JsonProperty("pares_direita")
    public List<String> paresDireita;
}