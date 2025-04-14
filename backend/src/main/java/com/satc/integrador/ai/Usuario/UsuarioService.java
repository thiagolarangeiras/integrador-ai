package com.satc.integrador.ai.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepo usuarioRepo;

    public UsuarioGetDto post(UsuarioPostDto dto) {
        Usuario usuario = Usuario.convertDtoToEntity(dto);
        usuario = usuarioRepo.save(usuario);
        return Usuario.convertEntityToDto(usuario);
    }

    public List<UsuarioGetDto> getAll(int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        return usuarioRepo.findAll(pageable).stream()
                .map(Usuario::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public UsuarioGetDto getOne(Integer id) {
        return usuarioRepo.findById(id)
                .map(Usuario::convertEntityToDto)
                .orElse(null);
    }

    public UsuarioGetDto patch(Integer id, UsuarioPostDto dto) {
        Usuario usuario = Usuario.convertDtoToEntity(dto);
        usuario.setId(id);
        usuario = usuarioRepo.save(usuario);
        return Usuario.convertEntityToDto(usuario);
    }

    public void delete(Integer id) {
        usuarioRepo.deleteById(id);
    }
}