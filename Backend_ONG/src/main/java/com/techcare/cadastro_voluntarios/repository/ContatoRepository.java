package com.gaa.backend.repository;

import com.gaa.backend.model.Contato;
import com.gaa.backend.enums.TipoContato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository responsável pela comunicação com o banco de dados
 * da entidade Contato.
 *
 * Utiliza Spring Data JPA para abstrair operações de persistência,
 * permitindo criação automática de queries com base nos nomes
 * dos métodos.
 *
 * Responsabilidades:
 * - Persistência de contatos
 * - Validação de duplicidade
 * - Pesquisas e filtros administrativos
 */
public interface ContatoRepository extends JpaRepository<Contato, Long> {

    /**
     * Verifica se já existe um contato cadastrado
     * com o valor informado.
     *
     * Exemplos:
     * - email@email.com
     * - (16) 99999-9999
     *
     * @param contato Valor do contato
     * @return true se já existir no banco
     */
    boolean existsByContato(String contato);

    /**
     * Busca contatos cujo valor contenha
     * o texto informado.
     *
     * A busca ignora diferenças entre letras
     * maiúsculas e minúsculas.
     *
     * Exemplos:
     *
     * Pesquisa: "gmail"
     * Resultado:
     * - pessoa@gmail.com
     * - contato@gmail.com
     *
     * Pesquisa: "9999"
     * Resultado:
     * - (16) 99999-1111
     * - (16) 99999-2222
     *
     * @param contato Texto pesquisado
     * @return Lista de contatos encontrados
     */
    List<Contato> findByContatoContainingIgnoreCase(String contato);

    /**
     * Busca contatos pelo tipo informado.
     *
     * Tipos disponíveis:
     * - EMAIL
     * - TELEFONE
     * - OUTRO
     *
     * @param tipo Tipo do contato
     * @return Lista de contatos encontrados
     */
    List<Contato> findByTipo(TipoContato tipo);

}