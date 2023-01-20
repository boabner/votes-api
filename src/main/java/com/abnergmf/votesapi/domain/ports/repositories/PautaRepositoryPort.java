package com.abnergmf.votesapi.domain.ports.repositories;

import java.util.List;

import com.abnergmf.votesapi.domain.Pauta;
public interface PautaRepositoryPort {
    List<Pauta> listarTodos();
    Pauta getById(Long id);
    Pauta persistir(Pauta pauta);
    Boolean remover(Pauta pauta);
}
