package com.gaa.backend.dto.response;

import com.gaa.backend.enums.TipoContato;
import lombok.Getter;
import lombok.Setter;

public class ContatoResponseDTO {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private TipoContato tipo;

    @Getter
    @Setter
    private String contato;

    @Getter
    @Setter
    private String descricao;

    public ContatoResponseDTO() {
    }

}