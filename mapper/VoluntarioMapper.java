package com.gaa.backend.mapper;

import com.gaa.backend.dto.VoluntarioRequestDTO;
import com.gaa.backend.dto.VoluntarioResponseDTO;
import com.gaa.backend.model.AreaAtuacao;
import com.gaa.backend.model.Voluntario;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class VoluntarioMapper {

    public static Voluntario toEntity(VoluntarioRequestDTO dto) {

        Voluntario voluntario = new Voluntario();

        voluntario.setNome(dto.getNome());
        voluntario.setCpf(dto.getCpf());
        voluntario.setProfissao(dto.getProfissao());
        voluntario.setRegistroConselho(dto.getRegistroConselho());
        voluntario.setHorasSemanaisDisponiveis(dto.getHorasSemanaisDisponiveis());
        voluntario.setStatus(dto.getStatus());

        // Mapeia contatos
        if (dto.getContatos() != null) {
            ContatoMapper contatoMapper = new ContatoMapper();
            voluntario.setContatos(
                dto.getContatos().stream()
                    .map(c -> {
                        var contato = contatoMapper.toEntity(c);
                        contato.setVoluntario(voluntario);
                        return contato;
                    })
                    .collect(Collectors.toList())
            );
        } else {
            voluntario.setContatos(new ArrayList<>());
        }

        // Mapeia endereços
        if (dto.getEnderecos() != null) {
            EnderecoMapper enderecoMapper = new EnderecoMapper();
            voluntario.setEnderecos(
                dto.getEnderecos().stream()
                    .map(e -> {
                        var endereco = enderecoMapper.toEntity(e);
                        endereco.setVoluntario(voluntario);
                        return endereco;
                    })
                    .collect(Collectors.toList())
            );
        } else {
            voluntario.setEnderecos(new ArrayList<>());
        }

        // Mapeia disponibilidades
        if (dto.getDisponibilidades() != null) {
            DisponibilidadeMapper dispMapper = new DisponibilidadeMapper();
            voluntario.setDisponibilidades(
                dto.getDisponibilidades().stream()
                    .map(d -> {
                        var disp = dispMapper.toEntity(d);
                        disp.setVoluntario(voluntario);
                        return disp;
                    })
                    .collect(Collectors.toList())
            );
        } else {
            voluntario.setDisponibilidades(new ArrayList<>());
        }

        // Áreas são resolvidas pelo service via IDs; inicializa lista vazia aqui
        voluntario.setAreas(new ArrayList<>());

        return voluntario;
    }

    public static VoluntarioResponseDTO toDTO(Voluntario voluntario) {

        VoluntarioResponseDTO dto = new VoluntarioResponseDTO();

        dto.setId(voluntario.getId());
        dto.setNome(voluntario.getNome());
        dto.setCpf(voluntario.getCpf());
        dto.setProfissao(voluntario.getProfissao());
        dto.setRegistroConselho(voluntario.getRegistroConselho());
        dto.setHorasSemanaisDisponiveis(voluntario.getHorasSemanaisDisponiveis());
        dto.setStatus(voluntario.getStatus());
        dto.setDataCriacao(voluntario.getDataCriacao());
        dto.setDataAtualizacao(voluntario.getDataAtualizacao());

        // Mapeia contatos
        ContatoMapper contatoMapper = new ContatoMapper();
        if (voluntario.getContatos() != null) {
            dto.setContatos(
                voluntario.getContatos().stream()
                    .map(contatoMapper::toResponseDTO)
                    .collect(Collectors.toList())
            );
        } else {
            dto.setContatos(new ArrayList<>());
        }

        // Mapeia endereços
        EnderecoMapper enderecoMapper = new EnderecoMapper();
        if (voluntario.getEnderecos() != null) {
            dto.setEnderecos(
                voluntario.getEnderecos().stream()
                    .map(enderecoMapper::toResponseDTO)
                    .collect(Collectors.toList())
            );
        } else {
            dto.setEnderecos(new ArrayList<>());
        }

        // Mapeia disponibilidades
        DisponibilidadeMapper dispMapper = new DisponibilidadeMapper();
        if (voluntario.getDisponibilidades() != null) {
            dto.setDisponibilidades(
                voluntario.getDisponibilidades().stream()
                    .map(dispMapper::toResponseDTO)
                    .collect(Collectors.toList())
            );
        } else {
            dto.setDisponibilidades(new ArrayList<>());
        }

        // Mapeia nomes das áreas
        if (voluntario.getAreas() != null) {
            dto.setAreasAtuacao(
                voluntario.getAreas().stream()
                    .map(AreaAtuacao::getNomeArea)
                    .collect(Collectors.toList())
            );
        } else {
            dto.setAreasAtuacao(new ArrayList<>());
        }

        return dto;
    }
}
