package com.gaa.backend.repository;

import com.gaa.backend.model.AreaAtuacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository responsável pela comunicação com o banco de dados
 * da entidade AreaAtuacao.
 *
 * Utiliza Spring Data JPA para abstrair operações de persistência,
 * permitindo criação automática de queries com base no nome
 * dos métodos.
 *
 * Responsabilidades:
 * - Persistência de áreas de atuação
 * - Consultas de especialidades disponíveis
 * - Pesquisas e validações de unicidade
 */
public interface AreaAtuacaoRepository extends JpaRepository<AreaAtuacao, Long> {

    /**
     * Busca uma área de atuação pelo nome exato.
     *
     * Query gerada automaticamente:
     * SELECT * FROM area_atuacao WHERE nome_area = ?
     *
     * Uso:
     * - Buscar área para associação com voluntários
     * - Recuperar registros específicos
     *
     * @param nomeArea Nome exato da área
     * @return Optional contendo a área, caso encontrada
     */
    Optional<AreaAtuacao> findByNomeArea(String nomeArea);

    /**
     * Verifica se já existe uma área cadastrada
     * com o nome informado.
     *
     * Query gerada automaticamente:
     * SELECT COUNT(*) > 0 FROM area_atuacao WHERE nome_area = ?
     *
     * Uso:
     * - Evitar duplicidade lógica no sistema
     *
     * @param nomeArea Nome da área
     * @return true se já existir, false caso contrário
     */
    boolean existsByNomeArea(String nomeArea);

    /**
     * Busca áreas de atuação cujo nome contenha
     * o texto informado.
     *
     * A busca ignora diferenças entre letras
     * maiúsculas e minúsculas.
     *
     * Exemplos:
     *
     * Pesquisa: "psico"
     * Resultado:
     * - Psicologia
     * - Neuropsicologia
     * - Psicopedagogia
     *
     * @param nomeArea Texto pesquisado
     * @return Lista de áreas encontradas
     */
    List<AreaAtuacao> findByNomeAreaContainingIgnoreCase(String nomeArea);

}