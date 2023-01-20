package com.abnergmf.votesapi.domain;

import java.util.Date;

import com.abnergmf.votesapi.domain.dtos.SessaoDTO;

public class Sessao {

    private Long id;
    private Date dataCriacao;
    private Date dataEncerramento;
    private Long pautaId;

    public static int TEMPO_SESSAO_ABERTA_DEFAULT = 1;

    public Sessao(Long id, Date dataCriacao, Date dataEncerramento, Long pautaId) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.dataEncerramento = dataEncerramento;
        this.pautaId = pautaId;
    }

    public Sessao(SessaoDTO sessaoDTO) {
        this.id = sessaoDTO.getId();
        this.dataCriacao = sessaoDTO.getDataCriacao();
        this.dataEncerramento = sessaoDTO.getDataEncerramento();
        this.pautaId = sessaoDTO.getPautaId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataEncerramento() {
        return dataEncerramento;
    }

    public void setDataEncerramento(Date dataEncerramento) {
        this.dataEncerramento = dataEncerramento;
    }

    public Long getPautaId() {
        return pautaId;
    }

    public void setPautaId(Long pautaId) {
        this.pautaId = pautaId;
    }
}
