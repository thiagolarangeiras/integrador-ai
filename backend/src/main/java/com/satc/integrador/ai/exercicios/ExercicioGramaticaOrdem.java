package com.satc.integrador.ai.exercicios;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//{
//    "tipo": "GRAMATICA_ORDEM",
//    "dados": {
//        "frase_completa": "Social media platforms influence how people communicate.",
//        "ordem_correta": ["Social", "media", "platforms", "influence", "how", "people", "communicate."],
//        "ordem_aleatoria": ["communicate.", "media", "Social", "influence", "platforms", "how", "people"]
//    }
//},

@Entity
@Table(name = "exercicio_gramatica_ordem")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExercicioGramaticaOrdem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer idOrdemExercicio;
    private Integer idPlanoEstudo;

    private String fraseCompleta;
    private List<String> ordemCorreta;
    private List<String> ordemAleatoria;

    public static ExercicioGramaticaOrdemGetDto mapToDto(ExercicioGramaticaOrdem obj) {
        return new ExercicioGramaticaOrdemGetDto(
                obj.getId(),
                obj.getIdOrdemExercicio(),
                obj.getIdPlanoEstudo(),
                obj.getFraseCompleta(),
                obj.getOrdemCorreta(),
                obj.getOrdemAleatoria()
        );
    }
}
