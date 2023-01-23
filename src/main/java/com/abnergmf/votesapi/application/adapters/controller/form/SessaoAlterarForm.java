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

    private String dataEncerramento;

    public Date getDataEncerramento() {
        return DateUtil.converterStringEmData(dataEncerramento);
    }

}
