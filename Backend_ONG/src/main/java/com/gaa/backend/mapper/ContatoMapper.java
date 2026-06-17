package com.gaa.backend.mapper;

import com.gaa.backend.dto.ContatoRequestDTO;
import com.gaa.backend.dto.ContatoResponseDTO;
import com.gaa.backend.model.Contato;
import org.springframework.stereotype.Component;

/**
 * Mapper responsável pela conversão
 * entre Contato e seus DTOs.
 */
@Component
public class ContatoMapper {

    public Contato toEntity(ContatoRequestDTO dto) {

        if (dto == null) {
            return null;
        }

        Contato contato = new Contato();

        contato.setTipo(dto.getTipo());
        contato.setContato(dto.getContato());
        contato.setDescricao(dto.getDescricao());

        return contato;
    }

    public ContatoResponseDTO toResponseDTO(Contato contato) {

        if (contato == null) {
            return null;
        }

        ContatoResponseDTO dto = new ContatoResponseDTO();

        dto.setId(contato.getId());
        dto.setTipo(contato.getTipo());
        dto.setContato(contato.getContato());
        dto.setDescricao(contato.getDescricao());

        return dto;
    }
}