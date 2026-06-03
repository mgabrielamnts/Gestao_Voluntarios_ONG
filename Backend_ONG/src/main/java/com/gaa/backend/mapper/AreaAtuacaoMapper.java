package com.gaa.backend.mapper;

import com.gaa.backend.dto.AreaAtuacaoRequestDTO;
import com.gaa.backend.dto.AreaAtuacaoResponseDTO;
import com.gaa.backend.model.AreaAtuacao;

/**
 * Mapper responsável pela conversão da entidade AreaAtuacao.
 */
public class AreaAtuacaoMapper {

    public static AreaAtuacao toEntity(AreaAtuacaoRequestDTO dto) {

        AreaAtuacao area = new AreaAtuacao();

        area.setNomeArea(dto.getNomeArea());

        return area;
    }

    public static AreaAtuacaoResponseDTO toResponseDTO(
            AreaAtuacao area
    ) {

        AreaAtuacaoResponseDTO dto =
                new AreaAtuacaoResponseDTO();

        dto.setId(area.getId());
        dto.setNomeArea(area.getNomeArea());

        return dto;
    }
}
