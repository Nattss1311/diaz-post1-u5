package com.universidad.reservaslabs.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.universidad.reservaslabs.model.Laboratorio;
import com.universidad.reservaslabs.repository.LaboratorioRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/laboratorios")
public class LaboratorioController {

    private final LaboratorioRepository repo;

    public LaboratorioController(LaboratorioRepository repo) { 
        this.repo = repo; 
    }

    @GetMapping
    public List<Laboratorio> listar() { 
        return repo.findAll(); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<Laboratorio> obtener(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Laboratorio> crear(@RequestBody @Valid Laboratorio laboratorio) {
        return ResponseEntity.status(201).body(repo.save(laboratorio));
    }
}