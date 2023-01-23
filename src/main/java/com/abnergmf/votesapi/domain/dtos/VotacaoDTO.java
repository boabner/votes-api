package com.abnergmf.votesapi.domain.dtos;

public class VotacaoDTO {

    private Long id;
    private String escolha;
    private Long sessaoId;
    private Long associadoId;

    public VotacaoDTO(Long id, String escolha, Long sessaoId, Long associadoId) {
        this.id = id;
        this.escolha = escolha;
        this.sessaoId = sessaoId;
        this.associadoId = associadoId;
    }

    public VotacaoDTO(String escolha, Long sessaoId, Long associadoId) {
        this.escolha = escolha;
        this.sessaoId = sessaoId;
        this.associadoId = associadoId;
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
