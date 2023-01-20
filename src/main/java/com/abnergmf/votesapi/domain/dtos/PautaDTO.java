package com.abnergmf.votesapi.domain.dtos;

public class PautaDTO {

    private Long id;
    private final String nome;

    public PautaDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public PautaDTO(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

}
