package com.gaa.backend.dto;

import com.gaa.backend.enums.TipoContato;
import lombok.Getter;
import lombok.Setter;

public class ContatoRequestDTO {


    @Getter
    @Setter
    private TipoContato tipo;

    @Getter
    @Setter
    private String contato;

    @Getter
    @Setter
    private String descricao;

    public ContatoRequestDTO() {
    }

}