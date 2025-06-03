package com.satc.integrador.ai.exercicios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExercicioVocabualrioParesRepo extends JpaRepository<ExercicioVocabualrioPares, Integer> {
    @Query(value = "select * from exercicio_vocabulario_pares where id_plano_estudo = ?1", nativeQuery = true)
    List<ExercicioVocabualrioPares> findByPlanoEstudo(Integer idPlanoEstudo);
}