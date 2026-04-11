package com.donaton.msnecesidades.controller;

import com.donaton.msnecesidades.model.Necesidad;
import com.donaton.msnecesidades.service.NecesidadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;





@RestController
@RequestMapping("/necesidades")
@RequiredArgsConstructor
public class NecesidadController {

    private final NecesidadService service;

    @GetMapping
    public List<Necesidad> listar() {
        return service.listar();
    }

    @PostMapping
    public Necesidad crear(@RequestBody Necesidad necesidad) {
        return service.crear(
            necesidad.getRecurso(),
            necesidad.getCantidad(),
            necesidad.getUbicacion(),
            necesidad.getRegion(),
            necesidad.getReportadoPor()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Necesidad> obtener(@PathVariable Long id) {
        return service.listar().stream()
            .filter(n -> n.getId().equals(id))
            .findFirst()
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}