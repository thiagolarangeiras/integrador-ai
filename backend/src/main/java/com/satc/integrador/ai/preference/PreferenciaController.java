package com.satc.integrador.ai.preference;

import com.satc.integrador.ai.preference.dto.PreferenciaGetDto;
import com.satc.integrador.ai.preference.dto.PreferenciaPostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/preferencia")
public class PreferenciaController {
    @Autowired private PreferenciaService service;

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getOneFromUser(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(service.getOneFromUser(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getCurrent() {
        return ResponseEntity.ok(service.getCurrent());
    }

    @GetMapping("list")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getAllFromUser(@RequestParam Integer page, @RequestParam Integer count) {
        return ResponseEntity.ok(service.getAllFromUser(page, count));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> post(@RequestBody PreferenciaPostDto dto) {
        return ResponseEntity.ok(service.post(dto));
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> patch(@PathVariable("id") Integer id, @RequestBody PreferenciaPostDto dto) {
        return ResponseEntity.ok(service.patch(id, dto));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    //ADMIN ACCESS ONLY
    @GetMapping("adm/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PreferenciaGetDto> getOneAdm(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(service.getOneAdm(id));
    }

    @GetMapping("adm")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<PreferenciaGetDto>> getAllAdm(@RequestParam Integer page, @RequestParam Integer count) {
        return ResponseEntity.ok(service.getAllAdm(page, count));
    }

    @PostMapping("adm")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PreferenciaGetDto> postAdm(@RequestBody PreferenciaPostDto dto) {
        return ResponseEntity.ok(service.postAdm(dto));
    }

    @PatchMapping("adm/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PreferenciaGetDto> patchAdm(@PathVariable("id") Integer id, @RequestBody PreferenciaPostDto dto) {
        return ResponseEntity.ok(service.patchAdm(id, dto));
    }

    @DeleteMapping("adm/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PreferenciaGetDto> deleteAdm(@PathVariable("id") Integer id) {
        service.deleteAdm(id);
        return ResponseEntity.ok().build();
    }
}
