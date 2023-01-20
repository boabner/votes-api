package com.abnergmf.votesapi.domain.dtos;

import java.util.Date;

public class SessaoDTO {

    private Long id;
    private Date dataCriacao;
    private Date dataEncerramento;
    private Long pautaId;


    public SessaoDTO(Long id, Long pautaId, Date dataCriacao, Date dataEncerramento) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.dataEncerramento = dataEncerramento;
        this.pautaId = pautaId;
    }

    public SessaoDTO(Long pautaId, Date dataCriacao, Date dataEncerramento) {
        this.pautaId = pautaId;
        this.dataCriacao = dataCriacao;
        this.dataEncerramento = dataEncerramento;
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
