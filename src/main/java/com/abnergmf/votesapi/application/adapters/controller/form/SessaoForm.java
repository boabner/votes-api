package com.abnergmf.votesapi.application.adapters.controller.form;

import java.text.ParseException;
import java.util.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

import com.abnergmf.votesapi.application.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;

public class SessaoForm {

    public SessaoForm() {
    }

    public SessaoForm(Long pautaId, String dataCriacao, String dataEncerramento) {
        this.pautaId = pautaId;
        this.dataCriacao = dataCriacao;
        this.dataEncerramento = dataEncerramento;
    }

    @NotNull(message = "{nome.not.blank}")
    private Long pautaId;

    @NotNull(message = "{dataCriacao.not.null}")
    @NotEmpty(message = "{dataCriacao.not.blank}")
    @Pattern(regexp = "([0-9]{2})/([0-9]{2})/([0-9]{4})\\s([0-9]{2}):([0-9]{2}):([0-9]{2})", message = "{data.pattern.validation}")
    private String dataCriacao;

    @NotNull(message = "{dataEncerramento.not.null}")
    @NotEmpty(message = "{dataEncerramento.not.blank}")
    @Pattern(regexp = "([0-9]{2})/([0-9]{2})/([0-9]{4})\\s([0-9]{2}):([0-9]{2}):([0-9]{2})", message = "{data.pattern.validation}")
    private String dataEncerramento;

    public Long getPautaId() {
        return pautaId;
    }

    public Date getDataCriacao() {
        return DateUtil.parseStringToDate(dataCriacao);
    }

    public Date getDataEncerramento() {
        return DateUtil.parseStringToDate(dataCriacao);
    }

}
