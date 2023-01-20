package com.abnergmf.votesapi.domain.ports.interfaces;

import java.util.List;

import com.abnergmf.votesapi.domain.Pauta;
import com.abnergmf.votesapi.domain.dtos.PautaDTO;

public interface PautaServicePort {

    List<PautaDTO> listarPautas();
    Pauta criarPauta(PautaDTO pautaDTO);
    Pauta atualizarPauta(Long idPauta, PautaDTO pautaDTO);
    Boolean removerPauta(Long idPauta);

}
