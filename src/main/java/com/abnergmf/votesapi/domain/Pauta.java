package com.abnergmf.votesapi.domain;

import com.abnergmf.votesapi.domain.dtos.PautaDTO;

public class Pauta {

    private Long id;
    private String nome;

    public Pauta(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Pauta(PautaDTO pautaDTO) {
        this.id = pautaDTO.getId();
        this.nome = pautaDTO.getNome();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
