package com.abnergmf.votesapi.domain.dtos;

import java.util.Date;

public class SessaoAtivaDTO {

    private Long id;
    private Date dataEncerramento;
    private Long pautaId;

    public SessaoAtivaDTO(Long id, Date dataEncerramento, Long pautaId) {
        this.id = id;
        this.dataEncerramento = dataEncerramento;
        this.pautaId = pautaId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
