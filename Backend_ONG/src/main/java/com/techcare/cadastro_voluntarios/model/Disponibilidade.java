package com.techcare.cadastro_voluntarios.model;

import jakarta.persistence.*;

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
    private String horario;

    public Disponibilidade() { }

    public Disponibilidade(
            Integer idDisponibilidade,
            Voluntario voluntario,
            String diaSemana,
            String horario
    ) {
        this.idDisponibilidade = idDisponibilidade;
        this.voluntario = voluntario;
        this.diaSemana = diaSemana;
        this.horario = horario;
    }

    public Integer getIdDisponibilidade() {
        return idDisponibilidade;
    }

    public void setIdDisponibilidade(Integer idDisponibilidade) {
        this.idDisponibilidade = idDisponibilidade;
    }

    public Voluntario getVoluntario() {
        return voluntario;
    }

    public void setVoluntario(Voluntario voluntario) {
        this.voluntario = voluntario;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}