package com.gaa.backend.controller;

import com.gaa.backend.dto.request.EnderecoRequestDTO;
import com.gaa.backend.dto.response.EnderecoResponseDTO;
import com.gaa.backend.mapper.EnderecoMapper;
import com.gaa.backend.model.Endereco;
import com.gaa.backend.service.EnderecoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;
    private final EnderecoMapper enderecoMapper;

    public EnderecoController(EnderecoService enderecoService, EnderecoMapper enderecoMapper) {
        this.enderecoService = enderecoService;
        this.enderecoMapper = enderecoMapper;
    }

    @GetMapping
    public List<EnderecoResponseDTO> listarTodos() {
        return enderecoService.listarTodos()
                .stream()
                .map(enderecoMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EnderecoResponseDTO buscarPorId(@PathVariable Long id) {
        return enderecoMapper.toResponseDTO(enderecoService.buscarPorId(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EnderecoResponseDTO salvar(@RequestBody EnderecoRequestDTO dto) {
        Endereco endereco = enderecoMapper.toEntity(dto);
        return enderecoMapper.toResponseDTO(enderecoService.salvar(endereco));
    }

    @PutMapping("/{id}")
    public EnderecoResponseDTO atualizar(
            @PathVariable Long id,
            @RequestBody EnderecoRequestDTO dto
    ) {
        Endereco endereco = enderecoMapper.toEntity(dto);
        return enderecoMapper.toResponseDTO(enderecoService.atualizar(id, endereco));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        enderecoService.deletar(id);
    }
}
