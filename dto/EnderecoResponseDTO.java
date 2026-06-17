package com.gaa.backend.dto;

import lombok.Getter;
import lombok.Setter;

public class EnderecoResponseDTO {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String cep;

    @Getter
    @Setter
    private String cidade;

    @Getter
    @Setter
    private String bairro;

    @Getter
    @Setter
    private String logradouro;

    @Getter
    @Setter
    private String numero;

    @Getter
    @Setter
    private String complemento;


    public EnderecoResponseDTO() {
    }
}