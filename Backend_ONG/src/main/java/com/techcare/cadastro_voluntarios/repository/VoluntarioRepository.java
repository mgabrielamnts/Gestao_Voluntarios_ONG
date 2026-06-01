package com.gaa.backend.repository;

import com.gaa.backend.enums.StatusVoluntario;
import com.gaa.backend.model.Voluntario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository responsável pela comunicação com o banco de dados
 * da entidade Voluntario.
 *
 * Utiliza Spring Data JPA para abstrair operações de persistência,
 * permitindo criação automática de queries com base nos nomes
 * dos métodos.
 *
 * Responsabilidades:
 * - Persistência de voluntários
 * - Validação de unicidade de dados
 * - Pesquisas administrativas
 * - Filtros por área, status e informações cadastrais
 *
 * Essa entidade representa o núcleo principal do sistema,
 * concentrando os dados operacionais relacionados aos voluntários.
 */
public interface VoluntarioRepository extends JpaRepository<Voluntario, Long> {

    /**
     * Verifica se já existe um voluntário cadastrado
     * com o CPF informado.
     *
     * Uso:
     * - Evitar duplicidade de cadastro
     * - Validação de unicidade
     *
     * @param cpf CPF pesquisado
     * @return true se já existir, false caso contrário
     */
    boolean existsByCpf(String cpf);

    /**
     * Busca voluntários cujo CPF comece
     * com o valor informado.
     *
     * Exemplos:
     *
     * Pesquisa: "123"
     * Resultado:
     * - 12345678900
     * - 12399988877
     *
     * @param cpf Parte inicial do CPF
     * @return Lista de voluntários encontrados
     */
    List<Voluntario> findByCpfStartingWith(String cpf);

    /**
     * Busca voluntários cujo nome contenha
     * o texto informado.
     *
     * A busca ignora diferenças entre letras
     * maiúsculas e minúsculas.
     *
     * Exemplos:
     *
     * Pesquisa: "Maria"
     * Resultado:
     * - Maria Eduarda
     * - Ana Maria
     *
     * @param nome Nome pesquisado
     * @return Lista de voluntários encontrados
     */
    List<Voluntario> findByNomeContainingIgnoreCase(String nome);


    /**
     * Busca voluntários pelo status informado.
     *
     * Exemplos:
     * - ATIVO
     * - PENDENTE
     * - DESATIVADO
     *
     * @param status Status pesquisado
     * @return Lista de voluntários encontrados
     */
    List<Voluntario> findByStatus(StatusVoluntario status);

    /**
     * Busca voluntários cuja área de atuação
     * contenha o texto informado.
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
     *
     * @param nomeArea Nome da área pesquisada
     * @return Lista de voluntários encontrados
     */
    List<Voluntario> findByAreasNomeAreaContainingIgnoreCase(String nomeArea);

}