package com.abnergmf.votesapi.application.adapters.converter;

import com.abnergmf.votesapi.domain.Sessao;
import com.abnergmf.votesapi.domain.dtos.SessaoResultadoDTO;
import org.springframework.stereotype.Component;

@Component
public class SessaoResultadoDTOConverter {

    public SessaoResultadoDTO sessaoToSessaoResultadoDTO(Sessao sessao) {
        return new SessaoResultadoDTO(sessao.getId(), sessao.getDataCriacao(), sessao.getDataEncerramento(), sessao.getPautaId());
    }

}
