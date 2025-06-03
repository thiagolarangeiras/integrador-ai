package com.satc.integrador.ai.studyplan;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PlanoEstudoRepo extends JpaRepository<PlanoEstudo, Integer> {
    Page<PlanoEstudo> findByIdUsuario(Integer idUsuario, Pageable pageable);

    @Query(value = "select * from plano_estudo where id_usuario = ?1 and ativo = true LIMIT 1", nativeQuery = true)
    PlanoEstudo findByIdUsuarioActive(Integer idUsuario);

    @Query(value = "select * from plano_estudo where id = ?1 and id_usuario = ?2 LIMIT 1", nativeQuery = true)
    PlanoEstudo findByIdUsuario(Integer id, Integer idUsuario);
}