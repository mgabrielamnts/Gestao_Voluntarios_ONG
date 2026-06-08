package com.gaa.backend.controller;

import com.gaa.backend.dto.ContatoRequestDTO;
import com.gaa.backend.dto.ContatoResponseDTO;
import com.gaa.backend.mapper.ContatoMapper;
import com.gaa.backend.model.Contato;
import com.gaa.backend.service.ContatoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

    private final ContatoService contatoService;
    private final ContatoMapper contatoMapper;

    public ContatoController(ContatoService contatoService, ContatoMapper contatoMapper) {
        this.contatoService = contatoService;
        this.contatoMapper = contatoMapper;
    }

    @GetMapping
    public List<ContatoResponseDTO> listarTodos() {
        return contatoService.listarTodos()
                .stream()
                .map(contatoMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ContatoResponseDTO buscarPorId(@PathVariable Long id) {
        return contatoMapper.toResponseDTO(contatoService.buscarPorId(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContatoResponseDTO salvar(@RequestBody ContatoRequestDTO dto) {
        Contato contato = contatoMapper.toEntity(dto);
        return contatoMapper.toResponseDTO(contatoService.salvar(contato));
    }

    @PutMapping("/{id}")
    public ContatoResponseDTO atualizar(
            @PathVariable Long id,
            @RequestBody ContatoRequestDTO dto
    ) {
        Contato contato = contatoMapper.toEntity(dto);
        return contatoMapper.toResponseDTO(contatoService.atualizar(id, contato));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        contatoService.deletar(id);
    }
}
