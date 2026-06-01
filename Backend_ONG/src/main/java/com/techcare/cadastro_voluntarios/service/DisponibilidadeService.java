package com.gaa.backend.service;

/**
 * Service responsável pelas regras de negócio da aplicação.
 */


import com.gaa.backend.exception.ResourceNotFoundException;
import com.gaa.backend.model.Disponibilidade;
import com.gaa.backend.repository.DisponibilidadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisponibilidadeService {

    private final DisponibilidadeRepository disponibilidadeRepository;

    public DisponibilidadeService(DisponibilidadeRepository disponibilidadeRepository) {
        this.disponibilidadeRepository = disponibilidadeRepository;
    }

    public List<Disponibilidade> listarTodos() {
        return disponibilidadeRepository.findAll();
    }

    public Disponibilidade buscarPorId(Long id) {
        return disponibilidadeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Disponibilidade não encontrada"));
    }

    public Disponibilidade salvar(Disponibilidade disponibilidade) {
        return disponibilidadeRepository.save(disponibilidade);
    }

    public Disponibilidade atualizar(Long id, Disponibilidade disponibilidadeAtualizada) {

        Disponibilidade disponibilidade = buscarPorId(id);

        disponibilidade.setDiaSemana(disponibilidadeAtualizada.getDiaSemana());
        disponibilidade.setHorario(disponibilidadeAtualizada.getHorario());

        return disponibilidadeRepository.save(disponibilidade);
    }

    public void deletar(Long id) {

        Disponibilidade disponibilidade = buscarPorId(id);

        disponibilidadeRepository.delete(disponibilidade);
    }
}