package com.abnergmf.votesapi.application.adapters.controller.form;

import java.util.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessaoForm {

    @NotNull(message = "{nome.not.blank}")
    private Long pautaId;
    @NotNull
    @Pattern(regexp = "^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$", message = "{dataCriacao.not.blank}")
    private Date dataCriacao;
    @NotNull
    @Pattern(regexp = "^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$", message = "{dataEncerramento.not.blank}")
    private Date dataEncerramento;

}
