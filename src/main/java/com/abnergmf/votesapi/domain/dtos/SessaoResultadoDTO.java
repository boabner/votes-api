package com.abnergmf.votesapi.domain.dtos;

import java.util.Date;

import com.abnergmf.votesapi.application.util.DateUtil;

public class SessaoResultadoDTO {

    private Long id;
    private Date dataCriacao;
    private Date dataEncerramento;
    private Long pautaId;

    public SessaoResultadoDTO(Long id, Date dataCriacao, Date dataEncerramento, Long pautaId) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.dataEncerramento = dataEncerramento;
        this.pautaId = pautaId;
    }

    public Long getId() {
        return id;
    }

    public String getDataCriacao() {
        return DateUtil.converterDataEmString(dataCriacao);
    }

    public String getDataEncerramento() {
        return DateUtil.converterDataEmString(dataEncerramento);
    }

    public Long getPautaId() {
        return pautaId;
    }

}
