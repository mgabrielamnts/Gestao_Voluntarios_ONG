package com.gaa.backend.dto;

import com.gaa.backend.enums.StatusVoluntario;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class VoluntarioRequestDTO {

    @Getter
    @Setter
    private String nome;

    @Getter
    @Setter
    private String cpf;

    @Getter
    @Setter
    private String profissao;

    @Getter
    @Setter
    private String registroConselho;

    @Getter
    @Setter
    private Integer horasSemanaisDisponiveis;

    @Getter
    @Setter
    private StatusVoluntario status;

    @Getter
    @Setter
    private List<Long> areasAtuacaoIds;

    @Getter
    @Setter
    private List<ContatoRequestDTO> contatos;

    @Getter
    @Setter
    private List<EnderecoRequestDTO> enderecos;

    @Getter
    @Setter
    private List<DisponibilidadeRequestDTO> disponibilidades;

    public VoluntarioRequestDTO() {
    }

}