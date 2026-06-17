package com.gaa.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Entidade que representa uma localização frequentada pelos voluntários no sistema.
 *
 * Exemplos:
 * - Consultório A;
 * - Consultário B;
 * - Moradia Pessoal.
 *
 * Essa entidade é utilizada para armazenar e organizar
 * as localidades dispostas pelos voluntários registrados.
 */
@Data
@Entity
public class Endereco {

    /**
     * Identificador único do voluntário.
     *
     * Estratégia IDENTITY:
     * - O banco de dados é responsável por gerar o ID automaticamente
     * - Geralmente utilizado com auto-increment
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Armazena os dados (CEP, logadouro, número, bairro e cidade, estado) da localidade informada.
     */
    @NotBlank
    @Column(length = 8)
    private String cep;

    @NotBlank
    @Column(length = 255)
    private String logradouro;

    @Column(length = 100, nullable = false)
    private String bairro;

    @Column(length = 100, nullable = false)
    private String cidade;

    @Column(length = 10)
    private String numero;

    /**
     * Registra complementos para referenciar a localização:
     * Ex. Segundo andar de um consultório, oitava sala.
     */
    private String complemento;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_voluntario")
    private Voluntario voluntario;

}
