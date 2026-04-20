package com.gaa.backend.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class VoluntarioDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String profissao;

    @NotBlank
    @Size(min = 11, max = 11)
    private String cpf;

    private String telefoneCelular;

    @NotBlank
    private String cidade;

    @Min(1)
    private Integer horasSemanaisDisponiveis;
}