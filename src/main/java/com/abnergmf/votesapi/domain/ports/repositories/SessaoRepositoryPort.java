package com.abnergmf.votesapi.domain.ports.repositories;

import java.util.List;

import com.abnergmf.votesapi.domain.Sessao;

public interface SessaoRepositoryPort {
    List<Sessao> listarTodos();
    List<Sessao> listarSessoesAtivas();
    Sessao buscarSessaoPorPautaId(Long pautaId);
    Sessao getById(Long id);
    Sessao salvar(Sessao sessao);
}
