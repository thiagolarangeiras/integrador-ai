package com.satc.integrador.ai.user.dto;

import com.satc.integrador.ai.enums.Plano;
import lombok.Builder;

@Builder
public record UsuarioGetDto(
        Integer id,
        String username,
        String email,
        String nomeCompleto,
        Plano plano
) { }