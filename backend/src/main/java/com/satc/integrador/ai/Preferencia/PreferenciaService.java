package com.satc.integrador.ai.Preferencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PreferenciaService {
    @Autowired
    private PreferenciaRepo repo;

    public List<PreferenciaGetDto> getAll (int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        return repo.findAll(pageable)
                .stream()
                .map(Preferencia::mapToDto)
                .collect(Collectors.toList());
    }

    public PreferenciaGetDto getOne (Integer id) {
        Optional<Preferencia> clienteOptional = repo.findById(id);
        Preferencia cliente = clienteOptional.orElseThrow();;
        return Preferencia.mapToDto(cliente);
    }

    public PreferenciaGetDto post(PreferenciaPostDto dto) {
        Preferencia cliente = Preferencia.mapToObj(dto);
        cliente = repo.save(cliente);
        return Preferencia.mapToDto(cliente);
    }

    public PreferenciaGetDto patch(Integer id, PreferenciaPostDto dto) {
        Preferencia cliente = repo.findById(id).orElseThrow();
        cliente = Preferencia.mapToObj(dto);
        cliente.setId(id);
        cliente = repo.save(cliente);
        return Preferencia.mapToDto(cliente);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}