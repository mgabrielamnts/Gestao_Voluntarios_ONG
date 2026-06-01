package com.gaa.backend.service;

/**
 * Service responsável pelas regras de negócio da aplicação.
 */


import com.gaa.backend.exception.ResourceNotFoundException;
import com.gaa.backend.model.Contato;
import com.gaa.backend.repository.ContatoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContatoService {

    private final ContatoRepository contatoRepository;

    public ContatoService(ContatoRepository contatoRepository) {
        this.contatoRepository = contatoRepository;
    }

    public List<Contato> listarTodos() {
        return contatoRepository.findAll();
    }

    public Contato buscarPorId(Long id) {
        return contatoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado"));
    }

    public Contato salvar(Contato contato) {
        return contatoRepository.save(contato);
    }

    public Contato atualizar(Long id, Contato contatoAtualizado) {

        Contato contato = buscarPorId(id);

        contato.setTipo(contatoAtualizado.getTipo());
        contato.setContato(contatoAtualizado.getContato());
        contato.setDescricao(contatoAtualizado.getDescricao());

        return contatoRepository.save(contato);
    }

    public void deletar(Long id) {

        Contato contato = buscarPorId(id);

        contatoRepository.delete(contato);
    }
}