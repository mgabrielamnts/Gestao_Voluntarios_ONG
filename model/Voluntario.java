package com.gaa.backend.model;

import com.gaa.backend.enums.StatusVoluntario;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
// Biblioteca JPA (Java Persistence API)
// Responsável por mapear objetos Java para tabelas do banco de dados (ORM)

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
// Lombok: gera automaticamente getters, setters, equals, hashCode e toString
// Reduz código boilerplate

import java.time.LocalDate;
import java.time.LocalDateTime;
// Representa datas sem horário (ideal para datas de cadastro)

import java.util.List;

/**
 * Entidade que representa um voluntário no sistema.
 *
 * Essa classe será mapeada para uma tabela no banco de dados,
 * onde cada instância corresponde a um registro.
 *
 * Responsabilidades:
 * - Armazenar dados pessoais do voluntário
 * - Representar sua disponibilidade
 * - Relacionar com áreas de atuação
 */
@Data
@Entity
public class Voluntario {

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
     * Nome completo do voluntário.
     */
    @NotBlank
    @Column(nullable = false)
    private String nome;

    /**
     * Profissão do voluntário.
     */
    @NotBlank
    private String profissao;

    /**
     * CPF do voluntário.
     *
     * Regra de negócio:
     * - Deve ser único no sistema
     * - Evita duplicidade de cadastro
     *
     * Observação importante:
     * - A validação ideal deve existir também na camada de serviço,
     *   para evitar exceções diretas do banco
     */
    @NotBlank
    @Column(unique = true, nullable = false)
    private String cpf;


    /**
     * Registro no conselho profissional do voluntário.
     *
     * Campo opcional: nem todos os voluntários possuem
     * registro em conselho (ex.: áreas sem regulamentação).
     */
    @Column(length = 50)
    private String registroConselho;

    /**
     * Relação um-para-muitos com contatos.
     *
     * Um voluntário pode ter vários contatos,
     * Um contato pertence a um único voluntário.
     *
     *
     * joinColumns:
     * - Representa a chave estrangeira do voluntário.
     */
    @JsonManagedReference
    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "voluntario")
    private List<Contato> contatos;

    /**
     * Relação um-para-muitos com endereços.
     *
     * Um voluntário pode ter vários endereços,
     * Um endereço pode ter um voluntários.
     *
     * joinColumns:
     * - Representa a chave estrangeira do voluntario
     */
    @JsonManagedReference
    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "voluntario")
    private List<Endereco> enderecos;

    /**
     * Quantidade de horas semanais disponíveis para trabalho voluntário.
     *
     * Importante para alocação em atividades compatíveis com disponibilidade.
     */
    @NotNull
    private Integer horasSemanaisDisponiveis;

    /**
     * Data em que o voluntário foi cadastrado no sistema.
     *
     * Utiliza LocalDate pois não há necessidade de armazenar horário.
     */
    @NotNull
    private LocalDate dataCadastro = LocalDate.now();

    /**
     * Relação muitos-para-muitos com áreas de atuação.
     *
     * Um voluntário pode atuar em várias áreas,
     * e uma área pode ter vários voluntários.
     *
     * Tabela intermediária: area_voluntario
     *
     * joinColumns:
     * - Representa a chave estrangeira do voluntário
     *
     * inverseJoinColumns:
     * - Representa a chave estrangeira da área
     *
     * Estrutura da tabela gerada:
     * voluntario_area (
     *   id_voluntario,
     *   id_area
     * )
     *
     * Observação de modelagem:
     * - Essa abordagem evita duplicação de dados
     * - Mantém normalização do banco (3FN)
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "area_voluntario",
            joinColumns = @JoinColumn(name = "id_voluntario"),
            inverseJoinColumns = @JoinColumn(name = "id_area")
    )
    private List<AreaAtuacao> areas;

    /**
     * Relação um-para-muitos com disponibilidades.
     *
     * Um voluntário pode ter várias disponibilidades,
     * e uma disonibilidadee pode ter apenas um voluntários.
     *
     * joinColumn:
     * - Representa a chave estrangeira do voluntário
     *
     */
    @JsonManagedReference
    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "voluntario")
    private List<Disponibilidade> disponibilidades;



    private LocalDateTime dataCriacao;

    private LocalDateTime dataAtualizacao;

    @PrePersist
    public void prePersist() {
        dataCriacao = LocalDateTime.now();
        dataAtualizacao = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        dataAtualizacao = LocalDateTime.now();
    }

    @Enumerated(EnumType.STRING)
    private StatusVoluntario status;
}