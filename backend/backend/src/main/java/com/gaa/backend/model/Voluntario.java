package com.gaa.backend.model;

import jakarta.persistence.*; //Módulo de ___
import lombok.Data; //Módulo de ___
import java.time.LocalDate; //Módulo responsável por lidar com a data de cadastro do voluntário

@Data
@Entity
public class Voluntario {

    //Gera o id único do usuário
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome, profissao;

    //Faz com que o cpf seja único dentro do sistema
    @Column(unique = true)
    private String cpf;

    private String telefoneCelular;
    private String cidade;

    private Integer horasSemanaisDisponiveis;
    private LocalDate dataCadastro;
}