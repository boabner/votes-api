package com.abnergmf.votesapi.domain.ports.interfaces;

import java.util.List;

import com.abnergmf.votesapi.domain.Sessao;
import com.abnergmf.votesapi.domain.dtos.SessaoResultadoDTO;
import com.abnergmf.votesapi.domain.dtos.SessaoDTO;

public interface SessaoServicePort {

    List<SessaoResultadoDTO> listarSessaos();
    List<SessaoResultadoDTO> listarSessoesAtivas();
    public Sessao processarAberturaDeSessao(SessaoDTO sessaoDTO);
    SessaoDTO buscarSessaoPorPautaId(Long pautaID);
    Boolean validarSessaoAntesDeProsseguir(Long sessaoId);
    SessaoDTO prepararAberturaSessao(SessaoDTO sessaoDTO);

}
