package com.gaa.backend.controller;

import com.gaa.backend.dto.request.VoluntarioRequestDTO;
import com.gaa.backend.dto.response.VoluntarioResponseDTO;
import com.gaa.backend.mapper.VoluntarioMapper;
import com.gaa.backend.model.Voluntario;
import com.gaa.backend.service.VoluntarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/voluntarios")
public class VoluntarioController {

    private final VoluntarioService voluntarioService;

    public VoluntarioController(VoluntarioService voluntarioService) {
        this.voluntarioService = voluntarioService;
    }

    @GetMapping
    public Page<VoluntarioResponseDTO> listarTodos(Pageable pageable) {

        return voluntarioService
                .listarTodos(pageable)
                .map(VoluntarioMapper::toDTO);
    }

    @GetMapping("/{id}")
    public VoluntarioResponseDTO buscarPorId(@PathVariable Long id) {

        Voluntario voluntario = voluntarioService.buscarPorId(id);

        return VoluntarioMapper.toDTO(voluntario);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VoluntarioResponseDTO salvar(
            @RequestBody VoluntarioRequestDTO dto
    ) {
        Voluntario salvo = voluntarioService.salvar(dto);
        return VoluntarioMapper.toDTO(salvo);
    }

    @PutMapping("/{id}")
    public VoluntarioResponseDTO atualizar(
            @PathVariable Long id,
            @RequestBody VoluntarioRequestDTO dto
    ) {
        Voluntario atualizado = voluntarioService.atualizar(id, dto);
        return VoluntarioMapper.toDTO(atualizado);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {

        voluntarioService.deletar(id);
    }
}