package com.abnergmf.votesapi.domain.ports.repositories;

import java.util.Date;
import java.util.List;

import com.abnergmf.votesapi.domain.Sessao;

public interface SessaoRepositoryPort {
    List<Sessao> listarTodos();
    List<Sessao> listarSessoesAtivas();
    Sessao getById(Long id);
    Sessao persistir(Sessao sessao);
    Boolean remover(Sessao sessao);
}