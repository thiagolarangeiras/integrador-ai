package com.satc.integrador.ai.exercicios;

import com.satc.integrador.ai.exercicios.dto.ExercicioVocabualrioParesGetDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//{
//    "tipo": "VOCABULARIO_PARES",
//    "dados": {
//        "pares_esquerda": ["Social", "media", "platforms", "influence", "how", "people", "communicate"],
//        "pares_direita": ["Social", "midia", "plataforma", "influencia", "como", "pessoas", "comunicam"],
//    }
//},

@Entity
@Table(name = "exercicio_vocabulario_pares")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExercicioVocabualrioPares {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer idOrdemExercicio;
    private Integer idPlanoEstudo;

    private List<String> paresEsquerda;
    private List<String> paresDireita;
    private Boolean finalizado;

    public static ExercicioVocabualrioParesGetDto mapToDto(ExercicioVocabualrioPares obj) {
        return new ExercicioVocabualrioParesGetDto(
                obj.getId(),
                obj.getIdOrdemExercicio(),
                obj.getIdPlanoEstudo(),
                obj.getParesEsquerda(),
                obj.getParesDireita()
        );
    }
}