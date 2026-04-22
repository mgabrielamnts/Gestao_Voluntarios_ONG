package com.techcare.cadastro_voluntarios.dto;

import java.util.List;

public class VoluntarioUpdateRequest {

    private String nome;
    private String profissao;
    private String rg;
    private String registroConselho;
    private Integer horasSemanaisDisponiveis;

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