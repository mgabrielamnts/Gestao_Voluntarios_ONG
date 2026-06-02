package com.gaa.backend.dto.response;

import com.gaa.backend.enums.StatusVoluntario;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

public class VoluntarioResponseDTO {

    @Getter
    @Setter
    private Long id;

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
    private LocalDateTime dataCriacao;

    @Getter
    @Setter
    private LocalDateTime dataAtualizacao;

    @Getter
    @Setter
    private List<String> areasAtuacao;

    @Getter
    @Setter
    private List<ContatoResponseDTO> contatos;

    @Getter
    @Setter
    private List<EnderecoResponseDTO> enderecos;

    @Getter
    @Setter
    private List<DisponibilidadeResponseDTO> disponibilidades;

    public VoluntarioResponseDTO() {
    }

}