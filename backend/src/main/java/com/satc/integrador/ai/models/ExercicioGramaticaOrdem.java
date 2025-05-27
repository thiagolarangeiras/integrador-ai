package com.satc.integrador.ai.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
}
