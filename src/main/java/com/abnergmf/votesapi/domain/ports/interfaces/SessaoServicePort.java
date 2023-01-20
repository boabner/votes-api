package com.abnergmf.votesapi.domain.ports.interfaces;

import java.util.List;

import com.abnergmf.votesapi.domain.Sessao;
import com.abnergmf.votesapi.domain.dtos.SessaoDTO;

public interface SessaoServicePort {

    List<SessaoDTO> listarSessaos();
    Sessao criarSessao(SessaoDTO sessaoDTO);
    Sessao atualizarSessao(Long idSessao, SessaoDTO sessaoDTO);
    Boolean removerSessao(Long idSessao);

}
