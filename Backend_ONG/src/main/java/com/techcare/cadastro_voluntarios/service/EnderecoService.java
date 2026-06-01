package com.gaa.backend.service;

/**
 * Service responsável pelas regras de negócio da aplicação.
 */


import com.gaa.backend.exception.ResourceNotFoundException;
import com.gaa.backend.model.Endereco;
import com.gaa.backend.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public List<Endereco> listarTodos() {
        return enderecoRepository.findAll();
    }

    public Endereco buscarPorId(Long id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado"));
    }

    public Endereco salvar(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public Endereco atualizar(Long id, Endereco enderecoAtualizado) {

        Endereco endereco = buscarPorId(id);

        endereco.setCep(enderecoAtualizado.getCep());
        endereco.setCidade(enderecoAtualizado.getCidade());
        endereco.setBairro(enderecoAtualizado.getBairro());
        endereco.setLogradouro(enderecoAtualizado.getLogradouro());
        endereco.setNumero(enderecoAtualizado.getNumero());
        endereco.setComplemento(enderecoAtualizado.getComplemento());

        return enderecoRepository.save(endereco);
    }

    public void deletar(Long id) {

        Endereco endereco = buscarPorId(id);

        enderecoRepository.delete(endereco);
    }
}