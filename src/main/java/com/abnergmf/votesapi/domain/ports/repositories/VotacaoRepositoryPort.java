package com.abnergmf.votesapi.domain.ports.repositories;

import java.util.List;

import com.abnergmf.votesapi.domain.dtos.VotacaoDTO;
import com.abnergmf.votesapi.domain.votacao;

public interface VotacaoRepositoryPort {
    List<votacao> listarTodosPorSessaoId(Long sessaoId);
    votacao salvar(votacao votacao);
    boolean validarVotoValidoPorAssociadoIdESessaoId(VotacaoDTO votacaoDTO);
}
