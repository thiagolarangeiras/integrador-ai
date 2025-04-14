package com.satc.integrador.ai.Usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String password;
    private List<Plano> plano;

    //Mappers
    public static UsuarioGetDto convertEntityToDto(Usuario usuario) {
        return new UsuarioGetDto(
            usuario.getId(),
            usuario.getUsername(),
            usuario.getEmail(),
            usuario.getPlano()
        );
    }

    public static Usuario convertDtoToEntity(UsuarioPostDto dto) {
        Usuario u = new Usuario();
        u.setUsername(dto.username());
        u.setEmail(dto.email());
        u.setPassword(new BCryptPasswordEncoder().encode(dto.password()));
        u.setPlano(dto.plano());
        return u;
    }
}