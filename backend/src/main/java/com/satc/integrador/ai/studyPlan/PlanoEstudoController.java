package com.satc.integrador.ai.studyPlan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plano-estudo")
public class PlanoEstudoController {
    @Autowired private PlanoEstudoService service;

    @GetMapping("generate-new-plan")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> generateNewPlan() {
        return ResponseEntity.ok(service.generateNewPlan());
    }
}
