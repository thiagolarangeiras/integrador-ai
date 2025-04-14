package com.satc.integrador.ai.Usuario;

import lombok.Builder;

import java.util.List;

@Builder
public record UsuarioGetDto(
        Integer id,
        String username,
        String email,
        List<Plano> plano
) { }