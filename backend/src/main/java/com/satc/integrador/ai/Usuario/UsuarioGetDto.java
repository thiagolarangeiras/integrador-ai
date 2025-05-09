package com.satc.integrador.ai.Usuario;

import lombok.Builder;

@Builder
public record UsuarioGetDto(
        Integer id,
        String username,
        String email,
        String nomeCompleto,
        Plano plano
) { }