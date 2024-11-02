package org.example.mutant.controllers;

import org.example.mutant.services.DnaMutanteService;
import org.example.mutant.entities.Dna;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins ="*")
@RequestMapping(path="/mutant")
public class DnaMutanteController {

    private DnaMutanteService dnaMutanteService;

    public DnaMutanteController(DnaMutanteService dnaMutanteService) {
        this.dnaMutanteService = dnaMutanteService;
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Dna entity) {
        try {
            boolean isMutant = dnaMutanteService.detectarMutante(entity);
            if (isMutant) {
                return ResponseEntity.status(HttpStatus.OK).body("{\"message\":\"Mutante detectado\"}");
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{\"message\":\"No es mutante\"}");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
    @GetMapping
    public ResponseEntity<String> getMutantInfo() {
        return ResponseEntity.ok("El endpoint /mutant está funcionando");
    }
}

