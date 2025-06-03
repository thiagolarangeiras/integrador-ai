package com.satc.integrador.ai.chatgpt.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExercicioDtoGramaticaCompl implements IExerciciosGpt {
    @JsonProperty("frase_completa")
    public String fraseCompleta;

    @JsonProperty("frase_incompleta")
    public String fraseIncompleta;

    @JsonProperty("opcao_correta")
    public String opcaoCorreta;

    @JsonProperty("opcao_incorreta")
    public List<String> opcaoIncorreta;
}