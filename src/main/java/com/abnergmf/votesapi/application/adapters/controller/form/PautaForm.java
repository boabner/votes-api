package com.abnergmf.votesapi.application.adapters.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.abnergmf.votesapi.domain.dtos.PautaDTO;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class PautaForm {

    @NotNull
    @NotEmpty
    @Length(min = 1, max = 150)
    private String nome;

    public PautaDTO toPautaDTO() {
        return new PautaDTO(this.nome);
    }
}
