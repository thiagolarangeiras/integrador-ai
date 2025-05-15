package com.satc.integrador.ai.Usuario;

import com.satc.integrador.ai.Auth.SecurityUtil;
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

    public Integer getCurrentUserid() {
        return usuarioRepo.findByUsername(SecurityUtil.getCurrentUserSubject()).orElseThrow().getId();
    }

    public Boolean CheckIfAdminOrCurrentUser(){
        return CheckIfAdminOrCurrentUser(0);
    }
    public Boolean CheckIfAdminOrCurrentUser(Integer id){
        String userName = SecurityUtil.getCurrentUserSubject();
        Usuario u = usuarioRepo.findByUsername(userName).get();
        if (u.getPlano() == Plano.ADM) {
            return true;
        }
        if (u.getId() == id){
            return true;
        }
        return false;
    }

    public UsuarioGetDto postLogin(UsuarioPostDto dto) {
        Usuario usuario = Usuario.convertDtoToEntity(dto);
        usuario.setPlano(Plano.NORMAL);
        usuario = usuarioRepo.save(usuario);
        return Usuario.convertEntityToDto(usuario);
    }

    public UsuarioGetDto post(UsuarioPostDto dto) {
        if (!CheckIfAdminOrCurrentUser()){
            return null;
        }
        Usuario usuario = Usuario.convertDtoToEntity(dto);
        usuario = usuarioRepo.save(usuario);
        return Usuario.convertEntityToDto(usuario);
    }

    public UsuarioGetDto getCurrent() {
        String userName = SecurityUtil.getCurrentUserSubject();
        return usuarioRepo.findByUsername(userName)
                .map(Usuario::convertEntityToDto)
                .get();
    }

    public List<UsuarioGetDto> getAll(int page, int count) {
        if (!CheckIfAdminOrCurrentUser()){
            return null;
        }
        Pageable pageable = PageRequest.of(page, count);
        return usuarioRepo.findAll(pageable).stream()
                .map(Usuario::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public UsuarioGetDto getOne(Integer id) {
        if (CheckIfAdminOrCurrentUser(id)){
            return usuarioRepo.findById(id)
                    .map(Usuario::convertEntityToDto)
                    .orElse(null);
        }
        return null;
    }

    public UsuarioGetDto patch(Integer id, UsuarioPostDto dto) {
        if (!CheckIfAdminOrCurrentUser(id)){
            return null;
        }
        Usuario usuario = Usuario.convertDtoToEntity(dto);
        usuario.setId(id);
        usuario = usuarioRepo.save(usuario);
        return Usuario.convertEntityToDto(usuario);
    }

    public void delete(Integer id) {
        if (!CheckIfAdminOrCurrentUser(id)){
            return;
        }
        usuarioRepo.deleteById(id);
    }
}