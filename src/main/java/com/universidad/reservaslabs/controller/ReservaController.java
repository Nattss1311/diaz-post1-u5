package com.universidad.reservaslabs.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.universidad.reservaslabs.model.Reserva;
import com.universidad.reservaslabs.service.ReservaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaService service;

    public ReservaController(ReservaService service) { 
        this.service = service; 
    }

    @GetMapping
    public List<Reserva> listar() { 
        return service.findAll(); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> obtener(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/laboratorio/{laboratorioId}")
    public List<Reserva> porLaboratorio(@PathVariable Long laboratorioId) {
        return service.findByLaboratorio(laboratorioId);
    }

    @PostMapping
    public ResponseEntity<Reserva> crear(@RequestBody @Valid Reserva reserva) {
        return ResponseEntity.status(201).body(service.crear(reserva));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        service.cancelar(id);
        return ResponseEntity.noContent().build();
    }
}