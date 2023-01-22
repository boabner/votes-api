package com.abnergmf.votesapi.application.adapters.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VotacaoForm {

    @NotNull(message = "{sessaoId.not.null}")
    private Long sessaoId;
    @NotNull
    @NotEmpty(message = "{escolha.not.blank}")
    @Length(min = 1, max = 1, message = "{escolha.max.lenght}")
    @Pattern(regexp = "[S,N]", message = "{escolha.invalid.value}")
    private String escolha;

}
