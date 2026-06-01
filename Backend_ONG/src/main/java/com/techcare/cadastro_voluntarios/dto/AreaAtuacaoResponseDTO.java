package com.gaa.backend.dto.response;

public class AreaAtuacaoResponseDTO {

    private Long id;

    private String nomeArea;

    public AreaAtuacaoResponseDTO() {
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getNomeArea() {
        return nomeArea;
    }

    public void setNomeArea(String nome) {
        this.nomeArea = nome;
    }

}