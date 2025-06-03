package com.satc.integrador.ai.studyplan;

import com.satc.integrador.ai.studyplan.dto.PlanoEstudoGetDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plano-estudo")
public class PlanoEstudoController {
    @Autowired private PlanoEstudoService service;

    @PostMapping("generate-new-plan")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PlanoEstudoGetDto> generateNewPlan() {
        return ResponseEntity.ok(service.generateNewPlan());
    }

    @GetMapping("today")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PlanoEstudoGetDto> get() {
        return ResponseEntity.ok(service.getCurrent());
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PlanoEstudoGetDto> getid(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getId(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<PlanoEstudoGetDto>> getAll(
            @RequestParam Integer page,
            @RequestParam Integer count
    ) {
        return ResponseEntity.ok(service.getAll(page, count));
    }

    @PostMapping("{id}/finalizar-exercicio/{id-exercicio}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> finalizarExercicio(
            @PathVariable Integer id,
            @PathVariable("id-exercicio") Integer idExercicio) {
        return ResponseEntity.ok(service.finalizarExercicio(id, idExercicio));
    }

    @PostMapping("{id}/finalizar")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> finalizarPlanoDiario(@PathVariable Integer id) {
        return ResponseEntity.ok(service.finalizarPlanoDiario(id));
    }
}