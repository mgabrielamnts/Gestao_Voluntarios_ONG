package com.gaa.backend.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entidade que representa uma área de atuação disponível
 * para os voluntários no sistema.
 *
 * Exemplos:
 * - Psicologia
 * - Direito
 * - Assistência Social
 *
 * Essa entidade é utilizada para classificar e organizar
 * os voluntários conforme suas especialidades.
 */
@Data
@Entity
public class AreaAtuacao {

    /**
     * Identificador único da área de atuação.
     *
     * Estratégia IDENTITY:
     * - O banco de dados gera automaticamente (auto-increment)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome da área de atuação.
     *
     * Regra de negócio:
     * - Deve representar uma especialidade clara
     * - Idealmente única no sistema (evitar duplicidade)
     *
     * Exemplo:
     * "Psicologia", "Educação", "Saúde"
     *
     * Observação:
     * Pode futuramente receber constraint UNIQUE no banco
     * para evitar áreas duplicadas.
     */
    @Column(nullable = false, unique = true, length = 100)
    private String nomeArea;
}