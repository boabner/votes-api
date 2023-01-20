package com.abnergmf.votesapi.application.adapters.controller.form;

import java.util.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.abnergmf.votesapi.application.util.DateUtil;

public class SessaoAlterarForm {

    public SessaoAlterarForm() {
    }

    public SessaoAlterarForm(String dataEncerramento) {
        this.dataEncerramento = dataEncerramento;
    }

    @NotNull(message = "{dataEncerramento.not.null}")
    @NotEmpty(message = "{dataEncerramento.not.blank}")
    @Pattern(regexp = "([0-9]{2})/([0-9]{2})/([0-9]{4})\\s([0-9]{2}):([0-9]{2}):([0-9]{2})", message = "{data.pattern.validation}")
    private String dataEncerramento;

    public Date getDataEncerramento() {
        return DateUtil.getDataValidada(dataEncerramento);
    }

}
