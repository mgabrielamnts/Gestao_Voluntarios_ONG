package com.techcare.cadastro_voluntarios.mapper;

import com.techcare.cadastro_voluntarios.dto.VoluntarioRequest;
import com.techcare.cadastro_voluntarios.dto.VoluntarioResponse;
import com.techcare.cadastro_voluntarios.dto.TelefoneResponse;
import com.techcare.cadastro_voluntarios.dto.DisponibilidadeResponse;
import com.techcare.cadastro_voluntarios.model.Voluntario;
import com.techcare.cadastro_voluntarios.model.TelefoneVoluntario;
import com.techcare.cadastro_voluntarios.model.Disponibilidade;
import com.techcare.cadastro_voluntarios.model.AreaAtuacao;

import java.util.stream.Collectors;

public class VoluntarioMapper {

    public static Voluntario toEntity(VoluntarioRequest req) {
        Voluntario v = new Voluntario();
        v.setNome(req.getNome());
        v.setProfissao(req.getProfissao());
        v.setRg(req.getRg());
        v.setCpf(req.getCpf());
        v.setRegistroConselho(req.getRegistroConselho());
        v.setCep(req.getCep());
        v.setLogradouro(req.getLogradouro());
        v.setNumeroResidencial(req.getNumeroResidencial());
        v.setBairro(req.getBairro());
        v.setCidade(req.getCidade());
        v.setHorasSemanaisDisponiveis(req.getHorasSemanaisDisponiveis());

        if (req.getTelefones() != null) {
            v.setTelefones(req.getTelefones().stream()
                    .map(t -> new TelefoneVoluntario(null, v,
                            t.getTelefoneResidencial(),
                            t.getTelefonePessoal(),
                            t.getTelefoneConsultorio()))
                    .collect(Collectors.toList()));
        }

        if (req.getDisponibilidades() != null) {
            v.setDisponibilidades(req.getDisponibilidades().stream()
                    .map(d -> new Disponibilidade(null, v,
                            d.getDiaSemana(),
                            d.getHorario()))
                    .collect(Collectors.toList()));
        }

        return v;
    }

    public static VoluntarioResponse toResponse(Voluntario v) {
        VoluntarioResponse resp = new VoluntarioResponse();
        resp.setIdVoluntario(v.getIdVoluntario());
        resp.setNome(v.getNome());
        resp.setProfissao(v.getProfissao());
        resp.setCpf(v.getCpf());
        resp.setRg(v.getRg());
        resp.setRegistroConselho(v.getRegistroConselho());
        resp.setCep(v.getCep());
        resp.setLogradouro(v.getLogradouro());
        resp.setNumeroResidencial(v.getNumeroResidencial());
        resp.setBairro(v.getBairro());
        resp.setCidade(v.getCidade());
        resp.setHorasSemanaisDisponiveis(v.getHorasSemanaisDisponiveis());
        resp.setDataCadastro(v.getDataCadastro());
        resp.setAtivo(v.getAtivo());

        if (v.getTelefones() != null) {
            resp.setTelefones(v.getTelefones().stream().map(t -> {
                TelefoneResponse tr = new TelefoneResponse();
                tr.setIdTelefoneVoluntario(t.getIdTelefoneVoluntario());
                tr.setTelefonePessoal(t.getTelefonePessoal());
                tr.setTelefoneResidencial(t.getTelefoneResidencial());
                tr.setTelefoneConsultorio(t.getTelefoneConsultorio());
                return tr;
            }).collect(Collectors.toList()));
        }

        if (v.getDisponibilidades() != null) {
            resp.setDisponibilidades(v.getDisponibilidades().stream().map(d -> {
                DisponibilidadeResponse dr = new DisponibilidadeResponse();
                dr.setIdDisponibilidade(d.getIdDisponibilidade());
                dr.setDiaSemana(d.getDiaSemana());
                dr.setHorario(d.getHorario());
                return dr;
            }).collect(Collectors.toList()));
        }

        resp.setAreas(v.getAreas().stream()
                .map(AreaAtuacao::getNomeArea)
                .collect(Collectors.toList()));

        return resp;
    }
}