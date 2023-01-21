package com.abnergmf.votesapi.domain.ports.interfaces;

import java.util.List;

import com.abnergmf.votesapi.domain.Votacao;
import com.abnergmf.votesapi.domain.dtos.VotacaoDTO;

public interface VotacaoServicePort {

    List<VotacaoDTO> listarVotosPorSessaoId(Long sessaoId);

    Votacao registrarVoto(VotacaoDTO votacaoDTO);

}
