package com.gaa.backend.dto.response;

import com.gaa.backend.enums.DiaSemana;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

public class DisponibilidadeResponseDTO {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private DiaSemana diaSemana;

    @Getter
    @Setter
    private LocalTime horario;

    public DisponibilidadeResponseDTO() {
    }
}