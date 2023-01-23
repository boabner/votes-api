package com.abnergmf.votesapi.domain;

import com.abnergmf.votesapi.domain.dtos.VotacaoDTO;

public class Votacao {

    private Long id;
    private String escolha;
    private Long sessaoId;

    public Votacao(Long id, String escolha, Long sessaoId) {
        this.id = id;
        this.escolha = escolha;
        this.sessaoId = sessaoId;
    }

    public Votacao(VotacaoDTO votacaoDTO) {
        this.id = votacaoDTO.getId();
        this.escolha = votacaoDTO.getEscolha();
        this.sessaoId = votacaoDTO.getSessaoId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSessaoId() {
        return sessaoId;
    }

    public void setSessaoId(Long sessaoId) {
        this.sessaoId = sessaoId;
    }

    public String getEscolha() {
        return escolha;
    }

    public void setEscolha(String escolha) {
        this.escolha = escolha;
    }

}
