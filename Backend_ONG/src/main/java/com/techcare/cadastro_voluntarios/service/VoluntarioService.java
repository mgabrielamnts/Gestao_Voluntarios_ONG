package com.gaa.backend.service;

import com.gaa.backend.dto.request.VoluntarioRequestDTO;
import com.gaa.backend.exception.ResourceNotFoundException;
import com.gaa.backend.mapper.VoluntarioMapper;
import com.gaa.backend.model.AreaAtuacao;
import com.gaa.backend.model.Contato;
import com.gaa.backend.model.Endereco;
import com.gaa.backend.model.Voluntario;
import com.gaa.backend.repository.AreaAtuacaoRepository;
import com.gaa.backend.repository.VoluntarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VoluntarioService {

    private final VoluntarioRepository voluntarioRepository;
    private final AreaAtuacaoRepository areaAtuacaoRepository;

    public VoluntarioService(
            VoluntarioRepository voluntarioRepository,
            AreaAtuacaoRepository areaAtuacaoRepository
    ) {
        this.voluntarioRepository = voluntarioRepository;
        this.areaAtuacaoRepository = areaAtuacaoRepository;
    }

    public Page<Voluntario> listarTodos(Pageable pageable) {
        return voluntarioRepository.findAll(pageable);
    }

    public Voluntario buscarPorId(Long id) {
        return voluntarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Voluntário não encontrado"));
    }

    public Voluntario salvar(VoluntarioRequestDTO dto) {

        Voluntario voluntario = VoluntarioMapper.toEntity(dto);

        // Resolve áreas a partir dos IDs informados
        if (dto.getAreasAtuacaoIds() != null && !dto.getAreasAtuacaoIds().isEmpty()) {
            List<AreaAtuacao> areas = new ArrayList<>();
            for (Long areaId : dto.getAreasAtuacaoIds()) {
                AreaAtuacao area = areaAtuacaoRepository.findById(areaId)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Área de atuação não encontrada: " + areaId));
                areas.add(area);
            }
            voluntario.setAreas(areas);
        }

        // Garante bidirecionalidade das relações
        if (voluntario.getContatos() != null) {
            for (Contato c : voluntario.getContatos()) {
                c.setVoluntario(voluntario);
            }
        }
        if (voluntario.getEnderecos() != null) {
            for (Endereco e : voluntario.getEnderecos()) {
                e.setVoluntario(voluntario);
            }
        }

        return voluntarioRepository.save(voluntario);
    }

    public Voluntario atualizar(Long id, VoluntarioRequestDTO dto) {

        Voluntario voluntario = buscarPorId(id);

        voluntario.setNome(dto.getNome());
        voluntario.setProfissao(dto.getProfissao());
        voluntario.setCpf(dto.getCpf());
        voluntario.setRegistroConselho(dto.getRegistroConselho());
        voluntario.setHorasSemanaisDisponiveis(dto.getHorasSemanaisDisponiveis());
        voluntario.setStatus(dto.getStatus());

        // Atualiza áreas
        if (dto.getAreasAtuacaoIds() != null) {
            List<AreaAtuacao> areas = new ArrayList<>();
            for (Long areaId : dto.getAreasAtuacaoIds()) {
                AreaAtuacao area = areaAtuacaoRepository.findById(areaId)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Área de atuação não encontrada: " + areaId));
                areas.add(area);
            }
            voluntario.setAreas(areas);
        }

        return voluntarioRepository.save(voluntario); // fix: save() estava faltando
    }

    public void deletar(Long id) {
        Voluntario voluntario = buscarPorId(id);
        voluntarioRepository.delete(voluntario);
    }
}
