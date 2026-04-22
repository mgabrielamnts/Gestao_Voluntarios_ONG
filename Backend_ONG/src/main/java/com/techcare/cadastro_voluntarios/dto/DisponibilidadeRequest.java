package com.techcare.cadastro_voluntarios.dto;

public class DisponibilidadeRequest {

    private String diaSemana;
    private String horario; // "HH:mm"

    public String getDiaSemana() { return diaSemana; }
    public void setDiaSemana(String diaSemana) { this.diaSemana = diaSemana; }

    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }
}