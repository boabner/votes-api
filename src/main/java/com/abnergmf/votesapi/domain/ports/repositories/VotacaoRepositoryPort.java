package com.abnergmf.votesapi.domain.ports.repositories;

import java.util.List;

import com.abnergmf.votesapi.domain.Votacao;

public interface VotacaoRepositoryPort {
    List<Votacao> listarTodosPorSessaoId(Long sessaoId);
    Votacao salvar(Votacao votacao);

}
