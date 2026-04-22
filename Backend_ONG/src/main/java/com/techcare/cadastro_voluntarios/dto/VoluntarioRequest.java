package com.techcare.cadastro_voluntarios.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

public class VoluntarioRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String profissao;

    @NotBlank
    private String rg;

    @NotBlank
    @Size(min = 11, max = 11, message = "CPF deve ter 11 dígitos")
    private String cpf;

    private String registroConselho;

    private Integer horasSemanaisDisponiveis;

    @NotNull
    @Valid
    private EnderecoRequest endereco;

    private List<TelefoneRequest> telefones;

    private List<DisponibilidadeRequest> disponibilidades;

    private List<Integer> areas;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getProfissao() { return profissao; }
    public void setProfissao(String profissao) { this.profissao = profissao; }

    public String getRg() { return rg; }
    public void setRg(String rg) { this.rg = rg; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getRegistroConselho() { return registroConselho; }
    public void setRegistroConselho(String registroConselho) { this.registroConselho = registroConselho; }

    public Integer getHorasSemanaisDisponiveis() { return horasSemanaisDisponiveis; }
    public void setHorasSemanaisDisponiveis(Integer horasSemanaisDisponiveis) { this.horasSemanaisDisponiveis = horasSemanaisDisponiveis; }

    public EnderecoRequest getEndereco() { return endereco; }
    public void setEndereco(EnderecoRequest endereco) { this.endereco = endereco; }

    public List<TelefoneRequest> getTelefones() { return telefones; }
    public void setTelefones(List<TelefoneRequest> telefones) { this.telefones = telefones; }

    public List<DisponibilidadeRequest> getDisponibilidades() { return disponibilidades; }
    public void setDisponibilidades(List<DisponibilidadeRequest> disponibilidades) { this.disponibilidades = disponibilidades; }

    public List<Integer> getAreas() { return areas; }
    public void setAreas(List<Integer> areas) { this.areas = areas; }
}