package com.abnergmf.votesapi.application.adapters.converter;

import com.abnergmf.votesapi.application.adapters.controller.form.VotacaoForm;
import com.abnergmf.votesapi.domain.Votacao;
import com.abnergmf.votesapi.domain.dtos.VotacaoDTO;
import org.springframework.stereotype.Component;

@Component
public class VotacaoDTOConverter {

    public VotacaoDTO toVotacaoDTO(VotacaoForm votacaoForm) {
        return new VotacaoDTO(votacaoForm.getEscolha(), votacaoForm.getSessaoId());
    }

    public VotacaoDTO toVotacaoDTO(Votacao votacao) {
        return new VotacaoDTO(votacao.getId(), votacao.getEscolha(), votacao.getSessaoId());
    }
}
