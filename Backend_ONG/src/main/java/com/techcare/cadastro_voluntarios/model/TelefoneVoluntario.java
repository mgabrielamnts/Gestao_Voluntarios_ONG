package com.techcare.cadastro_voluntarios.model;

import jakarta.persistence.*;

@Entity
@Table(name = "telefone_voluntario")
public class TelefoneVoluntario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTelefoneVoluntario;

    @ManyToOne
    @JoinColumn(name = "id_voluntario")
    private Voluntario voluntario;

    private String telefoneResidencial;
    private String telefonePessoal;
    private String telefoneConsultorio;

    public TelefoneVoluntario() { }

    public TelefoneVoluntario(
            Integer idTelefoneVoluntario,
            Voluntario voluntario,
            String telefoneResidencial,
            String telefonePessoal,
            String telefoneConsultorio
    ) {
        this.idTelefoneVoluntario = idTelefoneVoluntario;
        this.voluntario = voluntario;
        this.telefoneResidencial = telefoneResidencial;
        this.telefonePessoal = telefonePessoal;
        this.telefoneConsultorio = telefoneConsultorio;
    }

    public Integer getIdTelefoneVoluntario() {
        return idTelefoneVoluntario;
    }

    public void setIdTelefoneVoluntario(Integer idTelefoneVoluntario) {
        this.idTelefoneVoluntario = idTelefoneVoluntario;
    }

    public Voluntario getVoluntario() {
        return voluntario;
    }

    public void setVoluntario(Voluntario voluntario) {
        this.voluntario = voluntario;
    }

    public String getTelefoneResidencial() {
        return telefoneResidencial;
    }

    public void setTelefoneResidencial(String telefoneResidencial) {
        this.telefoneResidencial = telefoneResidencial;
    }

    public String getTelefonePessoal() {
        return telefonePessoal;
    }

    public void setTelefonePessoal(String telefonePessoal) {
        this.telefonePessoal = telefonePessoal;
    }

    public String getTelefoneConsultorio() {
        return telefoneConsultorio;
    }

    public void setTelefoneConsultorio(String telefoneConsultorio) {
        this.telefoneConsultorio = telefoneConsultorio;
    }
}