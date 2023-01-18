package com.abnergmf.votesapi.domain.ports.interfaces;

import java.util.List;

import com.abnergmf.votesapi.domain.dtos.PautaDTO;

public interface PautaServicePort {

    List<PautaDTO> listarPautas();
    void criarPauta(PautaDTO pautaDTO);
    void atualizarPauta(Long idPauta, PautaDTO pautaDTO) throws Exception;
    void removerPauta(Long idPauta) throws Exception;

}
