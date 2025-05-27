package com.satc.integrador.ai.studyPlan;

import com.satc.integrador.ai.models.Usuario;
import com.satc.integrador.ai.preference.PreferenciaService;
import com.satc.integrador.ai.preference.dto.PreferenciaGetDto;
import com.satc.integrador.ai.repository.PlanoEstudoRepo;
import com.satc.integrador.ai.user.UsuarioService;
import com.satc.integrador.ai.user.dto.CreatedLoggedUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanoEstudoService {
    @Autowired private PlanoEstudoRepo repo;
    @Autowired private PreferenciaService preferenciaService;
    @Autowired private UsuarioService usuarioService;
    final String commandStudyPlan = "";

    public CreatedLoggedUserDto generateNewPlan() {
        Integer userId = usuarioService.getCurrentUserid();
        PreferenciaGetDto dtoPreferencia = preferenciaService.getCurrent();

        Usuario usuario = Usuario.convertDtoToEntity(dto);
        usuario = usuarioRepo.save(usuario);
        com.satc.integrador.ai.auth.dto.RecoveryJwtTokenDto jwtToken = authService.authenticateUser(dto.username(), dto.password());
        return new CreatedLoggedUserDto(usuario.getId(), usuario.getUsername(), usuario.getEmail(), usuario.getNomeCompleto(), usuario.getPlano(), jwtToken.token());
    }
}
