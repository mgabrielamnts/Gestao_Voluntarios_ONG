package com.techcare.cadastro_voluntarios.model;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "disponibilidade")
public class Disponibilidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDisponibilidade;

    @ManyToOne
    @JoinColumn(name = "id_voluntario")
    private Voluntario voluntario;

    private String diaSemana;
    private LocalTime horario;

    public Disponibilidade() { }

    public Disponibilidade(Integer idDisponibilidade, Voluntario voluntario,
                           String diaSemana, LocalTime horario) {
        this.idDisponibilidade = idDisponibilidade;
        this.voluntario = voluntario;
        this.diaSemana = diaSemana;
        this.horario = horario;
    }

    public Integer getIdDisponibilidade() { return idDisponibilidade; }
    public void setIdDisponibilidade(Integer idDisponibilidade) { this.idDisponibilidade = idDisponibilidade; }

    public Voluntario getVoluntario() { return voluntario; }
    public void setVoluntario(Voluntario voluntario) { this.voluntario = voluntario; }

    public String getDiaSemana() { return diaSemana; }
    public void setDiaSemana(String diaSemana) { this.diaSemana = diaSemana; }

    public LocalTime getHorario() { return horario; }
    public void setHorario(LocalTime horario) { this.horario = horario; }
}