package com.satc.integrador.ai.user.dto;

import com.satc.integrador.ai.enums.Plano;
import lombok.Builder;

@Builder
public record UsuarioPostDto(
        String username,
        String email,
        String password,
        String nomeCompleto,
        Plano plano
) { }