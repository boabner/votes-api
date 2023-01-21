package com.abnergmf.votesapi.domain.ports.interfaces;

import com.abnergmf.votesapi.domain.dtos.ResultadoVotacaoDTO;
import com.abnergmf.votesapi.domain.dtos.VotacaoDTO;

public interface VotacaoServicePort {

    ResultadoVotacaoDTO exibirResultadoPorSessaoId(Long sessaoId);

    void registrarVoto(VotacaoDTO votacaoDTO);

}
