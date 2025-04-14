package com.satc.integrador.ai.Preferencia;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PreferenciaRepo extends JpaRepository<Preferencia, Integer> {
    Optional<Page<Preferencia>> findById(Integer id, Pageable pageable);
}