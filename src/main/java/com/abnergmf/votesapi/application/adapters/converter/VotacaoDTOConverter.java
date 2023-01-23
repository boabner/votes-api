package com.abnergmf.votesapi.application.adapters.converter;

import com.abnergmf.votesapi.application.adapters.controller.form.VotacaoForm;
import com.abnergmf.votesapi.domain.dtos.VotacaoDTO;
import org.springframework.stereotype.Component;

@Component
public class VotacaoDTOConverter {

    public VotacaoDTO votacaoFormToVotacaoDTO(VotacaoForm votacaoForm) {
        return new VotacaoDTO(votacaoForm.getEscolha(), votacaoForm.getSessaoId());
    }

}
