package com.satc.integrador.ai.repository;

import com.satc.integrador.ai.models.Preferencia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PreferenciaRepo extends JpaRepository<Preferencia, Integer> {
    //Optional<Page<Preferencia>> findById(Integer id, Pageable pageable);
    Page<Preferencia> findByIdUsuario(Integer idUsuario, Pageable pageable);

    @Query(value = "select * from preferencias where id_usuario = ?1 and ativo = true LIMIT 1", nativeQuery = true)
    Preferencia findByIdUsuarioActive(Integer id);

    @Query(value = "select * from preferencias where id = ?1 and id_usuario = ?2 LIMIT 1", nativeQuery = true)
    Preferencia findByIdFromUser(Integer id, Integer idUsuario);
}