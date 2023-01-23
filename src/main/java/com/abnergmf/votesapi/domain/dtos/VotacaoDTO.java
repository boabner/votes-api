package com.abnergmf.votesapi.domain.dtos;

public class VotacaoDTO {

    private Long id;
    private String escolha;
    private Long sessaoId;

    public VotacaoDTO(Long id, String escolha, Long sessaoId) {
        this.id = id;
        this.escolha = escolha;
        this.sessaoId = sessaoId;
    }

    public VotacaoDTO(String escolha, Long sessaoId) {
        this.escolha = escolha;
        this.sessaoId = sessaoId;
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
