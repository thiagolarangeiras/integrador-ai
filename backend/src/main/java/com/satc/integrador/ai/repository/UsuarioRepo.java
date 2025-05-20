package com.satc.integrador.ai.repository;

import com.satc.integrador.ai.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByUsername(String Username);
}
