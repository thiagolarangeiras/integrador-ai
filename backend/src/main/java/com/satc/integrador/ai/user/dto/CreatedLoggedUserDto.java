package com.satc.integrador.ai.user.dto;

import com.satc.integrador.ai.enums.Plano;

public record CreatedLoggedUserDto(
        Integer id,
        String username,
        String email,
        String nomeCompleto,
        Plano plano,
        String jwtToken
) {}