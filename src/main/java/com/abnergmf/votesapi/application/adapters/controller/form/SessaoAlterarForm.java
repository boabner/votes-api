package com.abnergmf.votesapi.application.adapters.controller.form;

import java.util.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.abnergmf.votesapi.application.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;

public class SessaoAlterarForm {

    public SessaoAlterarForm(String dataEncerramento) {
        this.dataEncerramento = dataEncerramento;
    }

    @NotNull(message = "{dataEncerramento.not.null}")
    @NotEmpty(message = "{dataEncerramento.not.blank}")
    @JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
    @Pattern(regexp = "([0-9]{2})/([0-9]{2})/([0-9]{4})\\s([0-9]{2}):([0-9]{2}):([0-9]{2})", message = "{data.pattern.validation}")
    private String dataEncerramento;

    public Date getDataEncerramento() {
        return DateUtil.converterStringEmData(dataEncerramento);
    }

}
