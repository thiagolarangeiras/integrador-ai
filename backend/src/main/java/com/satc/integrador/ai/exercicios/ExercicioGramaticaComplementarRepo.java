package com.satc.integrador.ai.exercicios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExercicioGramaticaComplementarRepo extends JpaRepository<ExercicioGramaticaComplementar, Integer> {
    @Query(value = "select * from exercicio_gramatica_complementar where id_plano_estudo = ?1", nativeQuery = true)
    List<ExercicioGramaticaComplementar> findByPlanoEstudo(Integer idPlanoEstudo);

    @Query(value = "select * from exercicio_gramatica_complementar where id = ?1 and id_plano_estudo = ?2", nativeQuery = true)
    ExercicioGramaticaComplementar findByUsuario(Integer id, Integer idPlanoEstudo);
}