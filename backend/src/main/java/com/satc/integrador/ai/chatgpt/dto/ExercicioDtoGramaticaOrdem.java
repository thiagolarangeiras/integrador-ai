package com.satc.integrador.ai.chatgpt.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExercicioDtoGramaticaOrdem implements IExerciciosGpt {
    @JsonProperty("frase_completa")
    public String fraseCompleta;

    @JsonProperty("ordem_correta")
    public List<String> ordemCorreta;

    @JsonProperty("ordem_aleatoria")
    public List<String> ordemAleatoria;
}