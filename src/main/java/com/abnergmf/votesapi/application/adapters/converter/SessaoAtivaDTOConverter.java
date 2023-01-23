package com.abnergmf.votesapi.application.adapters.converter;

import com.abnergmf.votesapi.domain.Sessao;
import com.abnergmf.votesapi.domain.dtos.SessaoAtivaDTO;
import org.springframework.stereotype.Component;

@Component
public class SessaoAtivaDTOConverter {

    public SessaoAtivaDTO sessaoToSessaoAtivaDTO(Sessao sessao) {
        return new SessaoAtivaDTO(sessao.getId(), sessao.getDataEncerramento(), sessao.getPautaId());
    }

}
