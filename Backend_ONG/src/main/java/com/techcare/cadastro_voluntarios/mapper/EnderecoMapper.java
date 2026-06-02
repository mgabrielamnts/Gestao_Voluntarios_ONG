package com.gaa.backend.mapper;

import com.gaa.backend.dto.request.EnderecoRequestDTO;
import com.gaa.backend.dto.response.EnderecoResponseDTO;
import com.gaa.backend.model.Endereco;
import org.springframework.stereotype.Component;

/**
 * Mapper responsável pela conversão
 * entre Endereco e seus DTOs.
 */
@Component
public class EnderecoMapper {

    public Endereco toEntity(EnderecoRequestDTO dto) {

        if (dto == null) {
            return null;
        }

        Endereco endereco = new Endereco();

        endereco.setCep(dto.getCep());
        endereco.setCidade(dto.getCidade());
        endereco.setBairro(dto.getBairro());
        endereco.setLogradouro(dto.getLogradouro());
        endereco.setNumero(dto.getNumero());
        endereco.setComplemento(dto.getComplemento());
        endereco.setEstado(dto.getEstado());

        return endereco;
    }

    public EnderecoResponseDTO toResponseDTO(Endereco endereco) {

        if (endereco == null) {
            return null;
        }

        EnderecoResponseDTO dto = new EnderecoResponseDTO();

        dto.setId(endereco.getId());
        dto.setCep(endereco.getCep());
        dto.setCidade(endereco.getCidade());
        dto.setBairro(endereco.getBairro());
        dto.setLogradouro(endereco.getLogradouro());
        dto.setNumero(endereco.getNumero());
        dto.setComplemento(endereco.getComplemento());
        dto.setEstado(endereco.getEstado());

        return dto;
    }
}