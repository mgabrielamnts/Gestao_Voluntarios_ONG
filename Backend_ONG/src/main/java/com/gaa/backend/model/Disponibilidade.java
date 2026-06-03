package com.gaa.backend.model;

import com.gaa.backend.enums.DiaSemana;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalTime;

@Data
@Entity
public class Disponibilidade {

    /**
     * Identificador único do voluntário.
     *
     * Estratégia IDENTITY:
     * - O banco de dados é responsável por gerar o ID automaticamente
     * - Geralmente utilizado com autoincrement
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DiaSemana diaSemana;

    /**
     * Indica o horário do dia no formato 24h HH:MM através de um valor do tipo LocalTime.
     */
    @NotNull
    private LocalTime horario;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_voluntario")
    private Voluntario voluntario;

}
