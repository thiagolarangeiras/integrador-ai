package com.satc.integrador.ai.preference;

import com.satc.integrador.ai.preference.dto.PreferenciaGetDto;
import com.satc.integrador.ai.preference.dto.PreferenciaPostDto;
import com.satc.integrador.ai.user.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PreferenciaService {

    @Autowired
    private PreferenciaRepo repo;

    @Autowired
    private UsuarioService usuarioService;

    public List<PreferenciaGetDto> getAllFromUser(Integer page, Integer count) {
        Pageable pageable = PageRequest.of(page, count);
        return repo.findByIdUsuario(usuarioService.getCurrentUserid(), pageable)
                .stream()
                .map(Preferencia::mapToDto)
                .toList();
    }

    public PreferenciaGetDto getOneFromUser(Integer id) {
        Preferencia p = repo.findByIdFromUser(id, usuarioService.getCurrentUserid());
        return Preferencia.mapToDto(p);
    }

    public PreferenciaGetDto getCurrent() {
        Preferencia p = repo.findByIdUsuarioActive(usuarioService.getCurrentUserid());
        return Preferencia.mapToDto(p);
    }

    public PreferenciaGetDto post(PreferenciaPostDto dto) {
        Integer idUser = usuarioService.getCurrentUserid();
        Preferencia pActive = repo.findByIdUsuarioActive(idUser);
        if (pActive != null){
            pActive.setAtivo(false);
            repo.save(pActive);
        }
        Preferencia preferencia = Preferencia.mapToObj(dto);
        preferencia.setIdUsuario(idUser);
        preferencia.setAtivo(true);
        preferencia = repo.save(preferencia);
        return Preferencia.mapToDto(preferencia);
    }

    public PreferenciaGetDto patch(Integer id, PreferenciaPostDto dto) {
        Preferencia p = repo.findById(id).orElseThrow();
        Integer userId = usuarioService.getCurrentUserid();
        if (p.getIdUsuario() != userId){
            throw new AccessDeniedException("Usuario sem permissão");
        }
        p = Preferencia.mapToObj(dto);
        p.setId(id);
        p.setIdUsuario(userId);
        p = repo.save(p);
        return Preferencia.mapToDto(p);
    }

    public PreferenciaGetDto delete(Integer id) {
        Preferencia p = repo.findById(id).orElseThrow();
        if (p.getIdUsuario() != usuarioService.getCurrentUserid()){
            throw new AccessDeniedException("Usuario sem permissão");
        }
        repo.delete(p);
        return Preferencia.mapToDto(p);
    }

    public List<PreferenciaGetDto> getAllAdm(int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        return repo.findAll(pageable)
                .stream()
                .map(Preferencia::mapToDto)
                .collect(Collectors.toList());
    }

    public PreferenciaGetDto getOneAdm(Integer id) {
        Optional<Preferencia> clienteOptional = repo.findById(id);
        Preferencia cliente = clienteOptional.orElseThrow();;
        return Preferencia.mapToDto(cliente);
    }

    public PreferenciaGetDto postAdm(PreferenciaPostDto dto) {
        Preferencia preferencia = Preferencia.mapToObj(dto);
        preferencia = repo.save(preferencia);
        return Preferencia.mapToDto(preferencia);
    }

    public PreferenciaGetDto patchAdm(Integer id, PreferenciaPostDto dto) {
        Preferencia preferencia = repo.findById(id).orElseThrow();
        preferencia = Preferencia.mapToObj(dto);
        preferencia.setId(id);
        preferencia = repo.save(preferencia);
        return Preferencia.mapToDto(preferencia);
    }

    public void deleteAdm(Integer id) {
        repo.deleteById(id);
    }
}