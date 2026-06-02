package com.gaa.backend.dto.request;

import com.gaa.backend.enums.DiaSemana;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

public class DisponibilidadeRequestDTO {

    @Getter
    @Setter
    private DiaSemana diaSemana;

    @Getter
    @Setter
    private LocalTime horario;

    public DisponibilidadeRequestDTO() {
    }

}