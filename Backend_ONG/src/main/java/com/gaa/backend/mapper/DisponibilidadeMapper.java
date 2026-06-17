package com.gaa.backend.mapper;

import com.gaa.backend.dto.DisponibilidadeRequestDTO;
import com.gaa.backend.dto.DisponibilidadeResponseDTO;
import com.gaa.backend.model.Disponibilidade;
import org.springframework.stereotype.Component;

/**
 * Mapper responsável pela conversão
 * entre Disponibilidade e seus DTOs.
 */
@Component
public class DisponibilidadeMapper {

    public Disponibilidade toEntity(DisponibilidadeRequestDTO dto) {

        if (dto == null) {
            return null;
        }

        Disponibilidade disponibilidade = new Disponibilidade();

        disponibilidade.setDiaSemana(dto.getDiaSemana());
        disponibilidade.setHorario(dto.getHorario());

        return disponibilidade;
    }

    public DisponibilidadeResponseDTO toResponseDTO(
            Disponibilidade disponibilidade
    ) {

        if (disponibilidade == null) {
            return null;
        }

        DisponibilidadeResponseDTO dto = new DisponibilidadeResponseDTO();

        dto.setId(disponibilidade.getId());
        dto.setDiaSemana(disponibilidade.getDiaSemana());
        dto.setHorario(disponibilidade.getHorario());

        return dto;
    }
}