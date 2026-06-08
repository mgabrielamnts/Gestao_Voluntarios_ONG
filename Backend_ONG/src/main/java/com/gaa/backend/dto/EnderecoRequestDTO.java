package com.gaa.backend.dto;

import com.gaa.backend.enums.Estado;
import lombok.Getter;
import lombok.Setter;

public class EnderecoRequestDTO {

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

    @Getter
    @Setter
    private Estado estado;

    public EnderecoRequestDTO() {
    }

}