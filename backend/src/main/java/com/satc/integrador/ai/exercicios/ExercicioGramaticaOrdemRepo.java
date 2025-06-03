package com.satc.integrador.ai.exercicios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExercicioGramaticaOrdemRepo extends JpaRepository<ExercicioGramaticaOrdem, Integer> {
    @Query(value = "select * from exercicio_gramatica_ordem where id_plano_estudo = ?1", nativeQuery = true)
    List<ExercicioGramaticaOrdem> findByPlanoEstudo(Integer idPlanoEstudo);

    @Query(value = "select * from exercicio_gramatica_ordem where id = ?1 and id_plano_estudo = ?2", nativeQuery = true)
    ExercicioGramaticaOrdem findByUsuario(Integer id, Integer idPlanoEstudo);
}