package com.gaa.backend.service;

/**
 * Service responsável pelas regras de negócio da aplicação.
 */


import com.gaa.backend.exception.ResourceNotFoundException;
import com.gaa.backend.model.AreaAtuacao;
import com.gaa.backend.repository.AreaAtuacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaAtuacaoService {

    private final AreaAtuacaoRepository areaAtuacaoRepository;

    public AreaAtuacaoService(AreaAtuacaoRepository areaAtuacaoRepository) {
        this.areaAtuacaoRepository = areaAtuacaoRepository;
    }

    public List<AreaAtuacao> listarTodos() {
        return areaAtuacaoRepository.findAll();
    }

    public AreaAtuacao buscarPorId(Long id) {
        return areaAtuacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Área de atuação não encontrada"));
    }

    public AreaAtuacao salvar(AreaAtuacao areaAtuacao) {
        return areaAtuacaoRepository.save(areaAtuacao);
    }

    public AreaAtuacao atualizar(Long id, AreaAtuacao areaAtualizada) {

        AreaAtuacao area = buscarPorId(id);

        area.setNomeArea(areaAtualizada.getNomeArea());

        return areaAtuacaoRepository.save(area);
    }

    public void deletar(Long id) {

        AreaAtuacao area = buscarPorId(id);

        areaAtuacaoRepository.delete(area);
    }
}