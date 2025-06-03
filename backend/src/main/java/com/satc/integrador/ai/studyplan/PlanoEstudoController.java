package com.satc.integrador.ai.studyplan;

import com.satc.integrador.ai.studyplan.dto.PlanoEstudoGetDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plano-estudo")
public class PlanoEstudoController {
    @Autowired private PlanoEstudoService service;

    @PostMapping("generate-new-plan")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PlanoEstudoGetDto> generateNewPlan() {
        return ResponseEntity.ok(service.generateNewPlan());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PlanoEstudoGetDto> get() {
        return ResponseEntity.ok(service.getCurrent());
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PlanoEstudoGetDto> getid(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getId(id));
    }
}
