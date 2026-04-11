package com.gaa.backend.service;

import com.gaa.backend.dto.VoluntarioDTO;
import com.gaa.backend.model.Voluntario;
import com.gaa.backend.repository.VoluntarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VoluntarioService {

    private final VoluntarioRepository repository;

    public VoluntarioService(VoluntarioRepository repository) {
        this.repository = repository;
    }

    public Voluntario criar(VoluntarioDTO dto) {

        if (repository.existsByCpf(dto.getCpf())) {
            throw new RuntimeException("CPF já cadastrado");
        }

        Voluntario voluntario = converter(dto);
        voluntario.setDataCadastro(LocalDate.now());

        return repository.save(voluntario);
    }

    public List<Voluntario> listar() {
        return repository.findAll();
    }

    public Voluntario buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Voluntário não encontrado"));
    }

    public Voluntario atualizar(Long id, Voluntario novo) {
        Voluntario v = buscarPorId(id);

        v.setNome(novo.getNome());
        v.setProfissao(novo.getProfissao());
        v.setTelefoneCelular(novo.getTelefoneCelular());
        v.setCidade(novo.getCidade());

        return repository.save(v);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    private Voluntario converter(VoluntarioDTO dto) {
        Voluntario v = new Voluntario();

        v.setNome(dto.getNome());
        v.setProfissao(dto.getProfissao());
        v.setCpf(dto.getCpf());
        v.setTelefoneCelular(dto.getTelefoneCelular());
        v.setCidade(dto.getCidade());
        v.setHorasSemanaisDisponiveis(dto.getHorasSemanaisDisponiveis());

        return v;
    }
}