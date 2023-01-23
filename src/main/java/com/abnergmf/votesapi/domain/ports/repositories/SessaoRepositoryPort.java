package com.abnergmf.votesapi.domain.ports.repositories;

import java.util.List;

import com.abnergmf.votesapi.domain.Sessao;

public interface SessaoRepositoryPort {
    List<Sessao> listarTodos();
    List<Sessao> listarSessoesAtivas();
    List<Sessao> listarSessoesPorPautaId(Long pautaId);
    Sessao getById(Long id);
    Sessao salvar(Sessao sessao);
}
