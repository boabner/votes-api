package com.abnergmf.votesapi.domain.dtos;

import java.util.UUID;

public class PautaDTO {

    private UUID id;
    private Long idUsuarioCriador;
    private String nome;
    public PautaDTO(UUID id, Long idUsuarioCriador, String nome) {
        this.id = id;
        this.idUsuarioCriador = idUsuarioCriador;
        this.nome = nome;
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
}
