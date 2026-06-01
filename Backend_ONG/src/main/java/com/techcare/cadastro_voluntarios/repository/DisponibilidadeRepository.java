package com.gaa.backend.repository;

import com.gaa.backend.model.Disponibilidade;
import com.gaa.backend.enums.DiaSemana;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.List;

/**
 * Repository responsável pela comunicação com o banco de dados
 * da entidade Disponibilidade.
 *
 * Utiliza Spring Data JPA para abstrair operações de persistência,
 * permitindo criação automática de queries com base nos nomes
 * dos métodos.
 *
 * Responsabilidades:
 * - Persistência de horários disponíveis
 * - Filtros por dia da semana
 * - Pesquisas por horário
 * - Consultas operacionais de agenda
 */
public interface DisponibilidadeRepository extends JpaRepository<Disponibilidade, Long> {

    /**
     * Busca disponibilidades de um dia específico da semana.
     *
     * Exemplos:
     * - SEGUNDA
     * - TERCA
     * - QUARTA
     *
     * @param diaSemana Dia da semana pesquisado
     * @return Lista de disponibilidades encontradas
     */
    List<Disponibilidade> findByDiaSemana(DiaSemana diaSemana);

    /**
     * Busca disponibilidades por horário exato.
     *
     * Exemplo:
     * Pesquisa: 14:00
     *
     * Resultado:
     * - Segunda 14:00
     * - Quinta 14:00
     *
     * @param horario Horário pesquisado
     * @return Lista de disponibilidades encontradas
     */
    List<Disponibilidade> findByHorario(LocalTime horario);

    /**
     * POSSIVELMENTE DESNECESÁRIO!!!
     * Busca disponibilidades utilizando dia da semana
     * e horário simultaneamente.
     *
     * Exemplo:
     * Pesquisa:
     * - SEGUNDA
     * - 14:00
     *
     * Resultado:
     * - Segunda 14:00
     *
     * @param diaSemana Dia da semana
     * @param horario Horário pesquisado
     * @return Lista de disponibilidades encontradas
     */
    List<Disponibilidade> findByDiaSemanaAndHorario(
            DiaSemana diaSemana,
            LocalTime horario
    );

}