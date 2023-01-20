package com.abnergmf.votesapi.application.adapters.converter;

import java.util.Date;

import com.abnergmf.votesapi.application.adapters.controller.form.SessaoForm;
import com.abnergmf.votesapi.domain.Sessao;
import com.abnergmf.votesapi.domain.dtos.SessaoDTO;
import org.springframework.stereotype.Component;

@Component
public class SessaoDTOConverter {

    public SessaoDTO toSessaoDTO(SessaoForm sessaoForm) {
        return new SessaoDTO(sessaoForm.getPautaId(), sessaoForm.getDataCriacao(), sessaoForm.getDataEncerramento());
    }

    public SessaoDTO toSessaoDTO(Sessao sessao) {
        return new SessaoDTO(sessao.getId(), sessao.getPautaId(), sessao.getDataCriacao(), sessao.getDataEncerramento());
    }


}
