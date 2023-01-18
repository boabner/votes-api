package com.abnergmf.votesapi.domain;

import java.util.UUID;

import com.abnergmf.votesapi.domain.dtos.PautaDTO;

public class Pauta {

    private UUID id;
    private Long idUsuarioCriador;
    private String nome;

    public Pauta() {
    }

    public Pauta(UUID id, Long idUsuarioCriador, String nome) {
        this.id = id;
        this.idUsuarioCriador = idUsuarioCriador;
        this.nome = nome;
    }

    public Pauta(PautaDTO pautaDTO) {
        this.id = pautaDTO.getId();
        this.idUsuarioCriador = getIdUsuarioCriador();
        this.nome = getNome();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getIdUsuarioCriador() {
        return idUsuarioCriador;
    }

    public void setIdUsuarioCriador(Long idUsuarioCriador) {
        this.idUsuarioCriador = idUsuarioCriador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public PautaDTO toPautaDTO() {
        return new PautaDTO(this.id, this.idUsuarioCriador, this.nome);
    }
}
