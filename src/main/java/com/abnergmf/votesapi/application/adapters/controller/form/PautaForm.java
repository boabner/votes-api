package com.abnergmf.votesapi.application.adapters.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.abnergmf.votesapi.domain.dtos.PautaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PautaForm {

    @NotNull
    @NotEmpty(message = "{nome.not.blank}")
    @Length(min = 1, max = 150)
    private String nome;

}
