package com.gaa.backend.repository;

import com.gaa.backend.enums.Estado;
import com.gaa.backend.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository responsável pela comunicação com o banco de dados
 * da entidade Endereco.
 *
 * Utiliza Spring Data JPA para abstrair operações de persistência,
 * permitindo criação automática de queries com base nos nomes
 * dos métodos.
 *
 * Responsabilidades:
 * - Persistência de endereços
 * - Consultas administrativas simples
 * - Pesquisas regionais por cidade e bairro
 *
 * Observação:
 * Nesta aplicação, Endereco funciona como uma entidade auxiliar,
 * utilizada principalmente para exibição de informações vinculadas
 * ao voluntário.
 *
 * Por esse motivo, o repository foi mantido de forma enxuta,
 * contendo apenas filtros considerados úteis para consultas
 * administrativas e futuras expansões do sistema.
 */
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    /**
     * Busca endereços cuja cidade contenha
     * o texto informado.
     *
     * A busca ignora diferenças entre letras
     * maiúsculas e minúsculas.
     *
     * Exemplos:
     *
     * Pesquisa: "fran"
     * Resultado:
     * - Franca
     *
     * @param cidade Cidade pesquisada
     * @return Lista de endereços encontrados
     */
    List<Endereco> findByCidadeContainingIgnoreCase(String cidade);

    /**
     * Busca endereços cujo bairro contenha
     * o texto informado.
     *
     * A busca ignora diferenças entre letras
     * maiúsculas e minúsculas.
     *
     * Exemplos:
     *
     * Pesquisa: "centro"
     * Resultado:
     * - Centro
     * - Centro Sul
     *
     * @param bairro Bairro pesquisado
     * @return Lista de endereços encontrados
     */
    List<Endereco> findByBairroContainingIgnoreCase(String bairro);

    /**
     * Busca endereços cujo CEP contenha
     * o texto informado.
     *
     * A busca ignora diferenças entre letras
     * maiúsculas e minúsculas.
     *
     * Exemplos:
     *
     * Pesquisa: "1440"
     * Resultado:
     * - 14415-000
     * - 14417-000
     *
     * @param cep CEP pesquisado
     * @return Lista de endereços encontrados
     */
    List<Endereco> findByCepContainingIgnoreCase(String cep);

    /**
     * Busca endereços cujo Logradouro contenha
     * o texto informado.
     *
     * A busca ignora diferenças entre letras
     * maiúsculas e minúsculas.
     *
     * Exemplos:
     *
     * Pesquisa: "Rua Das"
     * Resultado:
     * - Rua das Rosas
     * - Rua das Tulipas
     * - Rua dos Girassois
     *
     * @param logradouro Logradouro (Rua, Travessa, Avenida, Etc.) pesquisado
     * @return Lista de endereços encontrados
     */
    List<Endereco> findByLogradouroContainingIgnoreCase(String logradouro);

    /**
     * Busca endereços cujo número contenha
     * o texto informado.
     *
     * A busca ignora diferenças entre letras
     * maiúsculas e minúsculas.
     *
     * Exemplos:
     *
     * Pesquisa: "15"
     * Resultado:
     * - 1532
     * - 0154
     * - A215
     *
     * @param numero Número pesquisado
     * @return Lista de endereços encontrados
     */
    List<Endereco> findByNumeroContainingIgnoreCase(String numero);

    /**
     * Busca endereços pelo estado informado.
     *
     * @param estado Estado pesquisado (enum Estado)
     * @return Lista de endereços encontrados
     */
    List<Endereco> findByEstado(Estado estado);
}