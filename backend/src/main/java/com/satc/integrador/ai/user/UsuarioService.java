package com.satc.integrador.ai.user;

import com.satc.integrador.ai.auth.AuthService;
import com.satc.integrador.ai.auth.dto.RecoveryJwtTokenDto;
import com.satc.integrador.ai.enums.Plano;
import com.satc.integrador.ai.models.Usuario;
import com.satc.integrador.ai.repository.UsuarioRepo;
import com.satc.integrador.ai.user.dto.CreatedLoggedUserDto;
import com.satc.integrador.ai.user.dto.UsuarioGetDto;
import com.satc.integrador.ai.user.dto.UsuarioPostDto;
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
    private UsuarioRepo usuarioRepo;

    @Autowired
    private AuthService authService;

    public Integer getCurrentUserid() {
        return usuarioRepo.findByUsername(SecurityUtil.getCurrentUserSubject()).orElseThrow().getId();
    }

    public Boolean checkIfAdminOrCurrentUser(){
        return true;
    }

    public Boolean checkIfAdminOrCurrentUser(Integer id){
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

    public CreatedLoggedUserDto post(UsuarioPostDto dto) {
        if (!checkIfAdminOrCurrentUser()){
            return null;
        }
        Usuario usuario = Usuario.convertDtoToEntity(dto);
        usuario = usuarioRepo.save(usuario);
        RecoveryJwtTokenDto jwtToken = authService.authenticateUser(dto.username(), dto.password());
        return new CreatedLoggedUserDto(usuario.getId(), usuario.getUsername(), usuario.getEmail(), usuario.getNomeCompleto(), usuario.getPlano(), jwtToken.token());
    }

    public UsuarioGetDto getCurrent() {
        String userName = SecurityUtil.getCurrentUserSubject();
        return usuarioRepo.findByUsername(userName)
                .map(Usuario::convertEntityToDto)
                .get();
    }

    public List<UsuarioGetDto> getAll(int page, int count) {
        if (!checkIfAdminOrCurrentUser()){
            return null;
        }
        Pageable pageable = PageRequest.of(page, count);
        return usuarioRepo.findAll(pageable).stream()
                .map(Usuario::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public UsuarioGetDto getOne(Integer id) {
        if (checkIfAdminOrCurrentUser(id)){
            return usuarioRepo.findById(id)
                    .map(Usuario::convertEntityToDto)
                    .orElse(null);
        }
        return null;
    }

    public UsuarioGetDto patch(Integer id, UsuarioPostDto dto) {
        if (!checkIfAdminOrCurrentUser(id)){
            return null;
        }
        Usuario usuario = Usuario.convertDtoToEntity(dto);
        usuario.setId(id);
        usuario = usuarioRepo.save(usuario);
        return Usuario.convertEntityToDto(usuario);
    }

    public void delete(Integer id) {
        if (!checkIfAdminOrCurrentUser(id)){
            return;
        }
        usuarioRepo.deleteById(id);
    }
}