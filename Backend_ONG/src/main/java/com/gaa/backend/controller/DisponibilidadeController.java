package com.gaa.backend.controller;

import com.gaa.backend.dto.DisponibilidadeRequestDTO;
import com.gaa.backend.dto.DisponibilidadeResponseDTO;
import com.gaa.backend.mapper.DisponibilidadeMapper;
import com.gaa.backend.model.Disponibilidade;
import com.gaa.backend.service.DisponibilidadeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/disponibilidades")
public class DisponibilidadeController {

    private final DisponibilidadeService disponibilidadeService;
    private final DisponibilidadeMapper disponibilidadeMapper;

    public DisponibilidadeController(
            DisponibilidadeService disponibilidadeService,
            DisponibilidadeMapper disponibilidadeMapper
    ) {
        this.disponibilidadeService = disponibilidadeService;
        this.disponibilidadeMapper = disponibilidadeMapper;
    }

    @GetMapping
    public List<DisponibilidadeResponseDTO> listarTodos() {
        return disponibilidadeService.listarTodos()
                .stream()
                .map(disponibilidadeMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public DisponibilidadeResponseDTO buscarPorId(@PathVariable Long id) {
        return disponibilidadeMapper.toResponseDTO(
                disponibilidadeService.buscarPorId(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DisponibilidadeResponseDTO salvar(@RequestBody DisponibilidadeRequestDTO dto) {
        Disponibilidade disp = disponibilidadeMapper.toEntity(dto);
        return disponibilidadeMapper.toResponseDTO(disponibilidadeService.salvar(disp));
    }

    @PutMapping("/{id}")
    public DisponibilidadeResponseDTO atualizar(
            @PathVariable Long id,
            @RequestBody DisponibilidadeRequestDTO dto
    ) {
        Disponibilidade disp = disponibilidadeMapper.toEntity(dto);
        return disponibilidadeMapper.toResponseDTO(
                disponibilidadeService.atualizar(id, disp));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        disponibilidadeService.deletar(id);
    }
}
