package com.gaa.backend.controller;

import com.gaa.backend.dto.AreaAtuacaoRequestDTO;
import com.gaa.backend.dto.AreaAtuacaoResponseDTO;
import com.gaa.backend.mapper.AreaAtuacaoMapper;
import com.gaa.backend.model.AreaAtuacao;
import com.gaa.backend.service.AreaAtuacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller responsável pelo gerenciamento de áreas de atuação.
 */
@RestController
@RequestMapping("/areas-atuacao")
public class AreaAtuacaoController {

    private final AreaAtuacaoService areaAtuacaoService;

    public AreaAtuacaoController(
            AreaAtuacaoService areaAtuacaoService
    ) {
        this.areaAtuacaoService = areaAtuacaoService;
    }

    @GetMapping
    public List<AreaAtuacaoResponseDTO> listarTodos() {

        return areaAtuacaoService.listarTodos()
                .stream()
                .map(AreaAtuacaoMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AreaAtuacaoResponseDTO buscarPorId(
            @PathVariable Long id
    ) {

        return AreaAtuacaoMapper.toResponseDTO(
                areaAtuacaoService.buscarPorId(id)
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AreaAtuacaoResponseDTO salvar(
            @RequestBody AreaAtuacaoRequestDTO dto
    ) {
        AreaAtuacao area = AreaAtuacaoMapper.toEntity(dto);
        return AreaAtuacaoMapper.toResponseDTO(areaAtuacaoService.salvar(area));
    }

    @PutMapping("/{id}")
    public AreaAtuacaoResponseDTO atualizar(
            @PathVariable Long id,
            @RequestBody AreaAtuacaoRequestDTO dto
    ) {
        AreaAtuacao area = AreaAtuacaoMapper.toEntity(dto);
        return AreaAtuacaoMapper.toResponseDTO(areaAtuacaoService.atualizar(id, area));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        areaAtuacaoService.deletar(id);
    }
}
