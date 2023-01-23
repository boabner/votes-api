package com.abnergmf.votesapi.domain.ports.interfaces;

import java.util.List;

import com.abnergmf.votesapi.domain.Sessao;
import com.abnergmf.votesapi.domain.dtos.SessaoAtivaDTO;
import com.abnergmf.votesapi.domain.dtos.SessaoDTO;

public interface SessaoServicePort {

    List<SessaoDTO> listarSessaos();
    List<SessaoAtivaDTO> listarSessoesAtivas();
    Sessao abrirSessao(SessaoDTO sessaoDTO);
    Boolean validarSessaoAntesDeProsseguir(Long sessaoId);
    SessaoDTO prepararAberturaSessao(SessaoDTO sessaoDTO);

}
