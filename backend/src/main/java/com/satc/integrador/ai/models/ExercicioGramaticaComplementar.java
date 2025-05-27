package com.satc.integrador.ai.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//{
//    "tipo": "completar uma frase", enum = 0
//    "dados": {
//        "frase_completa": "Social media platforms influence how people communicate.",
//        "frase_incompleta": "Social media platforms ... how people communicate.",
//        "opcao_correta": "influence",
//        "opcao_incorreta": ["create", "follow", "interrupt"]
//    }
//},

@Entity
@Table(name = "exercicio_gramatica_complementar")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExercicioGramaticaComplementar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer idOrdemExercicio;
    private Integer idPlanoEstudo;

    private String fraseCompleta;
    private String fraseIncompleta;
    private String opcaoCorreta;
    private List<String> opcaoIncorreta;
}