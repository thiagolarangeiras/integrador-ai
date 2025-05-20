package com.satc.integrador.ai.user;

import jakarta.annotation.Nullable;
import com.satc.integrador.ai.user.dto.UsuarioPostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getOne(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(usuarioService.getOne(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getAll(
            @Nullable @RequestParam Integer page,
            @Nullable @RequestParam Integer count
    ) {
        if (page == null || count == null){
            return ResponseEntity.ok(usuarioService.getCurrent());
        }
        return ResponseEntity.ok(usuarioService.getAll(page, count));
    }

// TODO Verificar necessidade do metodo abaixo
//    @PostMapping
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<Object> post(@RequestBody UsuarioPostDto dto) {
//        return ResponseEntity.ok(usuarioService.post(dto));
//    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> patch(
            @PathVariable("id") Integer id,
            @RequestBody UsuarioPostDto dto
    ) {
        return ResponseEntity.ok(usuarioService.patch(id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
        usuarioService.delete(id);
        return ResponseEntity.ok().build();
    }
}