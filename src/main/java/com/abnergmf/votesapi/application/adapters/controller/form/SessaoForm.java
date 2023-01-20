package com.abnergmf.votesapi.application.adapters.controller.form;

import java.util.Date;
import javax.validation.constraints.NotNull;

import com.abnergmf.votesapi.application.util.DateUtil;

public class SessaoForm {

    @NotNull(message = "{pautaId.not.null}")
    private Long pautaId;
    private String dataEncerramento;
    public Long getPautaId() {
        return pautaId;
    }

    public SessaoForm() {

    }

    public SessaoForm(Long pautaId, String dataEncerramento) {
        this.pautaId = pautaId;
        this.dataEncerramento = dataEncerramento;
    }

    public Date getDataEncerramento() {
        return DateUtil.converterStringEmData(dataEncerramento);
    }

}
