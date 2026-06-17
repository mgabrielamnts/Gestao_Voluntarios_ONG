package com.gaa.backend.model;

import com.gaa.backend.enums.TipoContato;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


/**
 * Entidade que representa uma opção de contato disponível com o voluntário
 * para a administração.
 *
 * Exemplos:
 * - isso.e.email@mail.com
 * - (10) 9444-3155
 * - @contatoTelegram
 * - @redeSocialInstagram
 *
 * Essa entidade é utilizada para armazenar e organizar
 * os métodos de contato com os voluntários registrados.
 */
@Data
@Entity
public class Contato {

    /**
     * Identificador único do contato.
     *
     * Estratégia IDENTITY:
     * - O banco de dados é responsável por gerar o ID automaticamente
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Estrutura completa do contato.
     * Exemplos:
     * - isso.e.email@mail.com
     * - (10) 9444-3155
     * - @contatoTelegram
     * - @redeSocialInstagram
     */
    @NotBlank
    @Column(nullable = false, unique = true)
    private String contato;


    @Enumerated(EnumType.STRING)
    private TipoContato tipo;

    /**
     * ADICIONAR POSSIVEIS RESTRIÇÕES DEPOIS!!!!
     * A descrição deve conter as informações referentes ao contato: tipo de e-mail ou telefone, caso seja outro, adicione o tipo de contato preferido (Ex: Telegram, Instagram, etc).
     */
    @Column(nullable = true, unique = false, length = 500)
    private String descricao;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_voluntario")
    private Voluntario voluntario;

}
