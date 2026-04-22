package com.techcare.cadastro_voluntarios.mapper;

import com.techcare.cadastro_voluntarios.dto.*;
import com.techcare.cadastro_voluntarios.model.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class VoluntarioMapper {

    public static Voluntario toEntity(VoluntarioRequest req) {
        Voluntario v = new Voluntario();
        v.setNome(req.getNome());
        v.setProfissao(req.getProfissao());
        v.setRg(req.getRg());
        v.setCpf(req.getCpf());
        v.setRegistroConselho(req.getRegistroConselho());
        v.setHorasSemanaisDisponiveis(req.getHorasSemanaisDisponiveis());

        // Endereço
        if (req.getEndereco() != null) {
            EnderecoRequest e = req.getEndereco();
            Endereco end = new Endereco();
            end.setVoluntario(v);
            end.setCep(e.getCep());
            end.setLogradouro(e.getLogradouro());
            end.setNumero(e.getNumero());
            end.setComplemento(e.getComplemento());
            end.setBairro(e.getBairro());
            end.setCidade(e.getCidade());
            end.setEstado(e.getEstado());
            v.getEnderecos().add(end);
        }

        // Telefones
        if (req.getTelefones() != null) {
            v.setTelefones(req.getTelefones().stream()
                    .map(t -> new TelefoneVoluntario(null, v,
                            t.getTelefoneResidencial(),
                            t.getTelefonePessoal(),
                            t.getTelefoneConsultorio()))
                    .collect(Collectors.toList()));
        }

        // Disponibilidades
        if (req.getDisponibilidades() != null) {
            v.setDisponibilidades(req.getDisponibilidades().stream()
                    .map(d -> new Disponibilidade(null, v,
                            d.getDiaSemana(),
                            LocalTime.parse(d.getHorario())))
                    .collect(Collectors.toList()));
        }

        // Áreas NÃO são setadas aqui — o VoluntarioService busca do banco e seta

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
        resp.setHorasSemanaisDisponiveis(v.getHorasSemanaisDisponiveis());
        resp.setDataCadastro(v.getDataCadastro());
        resp.setAtivo(v.getAtivo());

        // Endereço (pega o primeiro da lista)
        if (v.getEnderecos() != null && !v.getEnderecos().isEmpty()) {
            Endereco e = v.getEnderecos().get(0);
            EnderecoResponse er = new EnderecoResponse();
            er.setIdEndereco(e.getIdEndereco());
            er.setCep(e.getCep());
            er.setLogradouro(e.getLogradouro());
            er.setNumero(e.getNumero());
            er.setComplemento(e.getComplemento());
            er.setBairro(e.getBairro());
            er.setCidade(e.getCidade());
            er.setEstado(e.getEstado());
            resp.setEndereco(er);
        }

        // Telefones
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

        // Disponibilidades
        if (v.getDisponibilidades() != null) {
            resp.setDisponibilidades(v.getDisponibilidades().stream().map(d -> {
                DisponibilidadeResponse dr = new DisponibilidadeResponse();
                dr.setIdDisponibilidade(d.getIdDisponibilidade());
                dr.setDiaSemana(d.getDiaSemana());
                dr.setHorario(d.getHorario() != null ? d.getHorario().toString() : null);
                return dr;
            }).collect(Collectors.toList()));
        }

        // Áreas
        if (v.getAreas() != null) {
            resp.setAreas(v.getAreas().stream()
                    .map(AreaAtuacao::getNomeArea)
                    .collect(Collectors.toList()));
        }

        return resp;
    }
}