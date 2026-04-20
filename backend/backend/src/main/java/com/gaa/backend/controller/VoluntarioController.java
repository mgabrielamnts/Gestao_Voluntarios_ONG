package com.gaa.backend.controller;

import com.gaa.backend.dto.VoluntarioDTO;
import com.gaa.backend.model.Voluntario;
import com.gaa.backend.service.VoluntarioService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/voluntarios")
public class VoluntarioController {

    private final VoluntarioService service;

    public VoluntarioController(VoluntarioService service) {
        this.service = service;
    }

    @PostMapping
    public Voluntario criar(@RequestBody @Valid VoluntarioDTO dto) {
        return service.criar(dto);
    }

    @GetMapping
    public List<Voluntario> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Voluntario buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Voluntario atualizar(@PathVariable Long id, @RequestBody Voluntario voluntario) {
        return service.atualizar(id, voluntario);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}