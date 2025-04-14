package com.satc.integrador.ai.Usuario;

import lombok.Builder;

import java.util.List;

@Builder
public record UsuarioPostDto(
        String username,
        String email,
        String password,
        List<Plano> plano
) { }