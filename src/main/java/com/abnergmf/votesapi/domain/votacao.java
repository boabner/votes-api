package com.abnergmf.votesapi.domain;

import com.abnergmf.votesapi.domain.dtos.VotacaoDTO;

public class votacao {

    private Long id;
    private String escolha;
    private Long sessaoId;
    private Long associadoId;

    public votacao(Long id, String escolha, Long sessaoId, Long associadoId) {
        this.id = id;
        this.escolha = escolha;
        this.sessaoId = sessaoId;
        this.associadoId = associadoId;
    }

    public votacao(VotacaoDTO votacaoDTO) {
        this.id = votacaoDTO.getId();
        this.escolha = votacaoDTO.getEscolha();
        this.sessaoId = votacaoDTO.getSessaoId();
        this.associadoId = votacaoDTO.getAssociadoId();
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

    public Long getAssociadoId() {
        return associadoId;
    }

}
