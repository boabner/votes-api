package com.abnergmf.votesapi.application.adapters.converter;

import com.abnergmf.votesapi.application.adapters.controller.form.SessaoAlterarForm;
import com.abnergmf.votesapi.application.adapters.controller.form.SessaoForm;
import com.abnergmf.votesapi.domain.Sessao;
import com.abnergmf.votesapi.domain.dtos.SessaoDTO;
import org.springframework.stereotype.Component;

@Component
public class SessaoDTOConverter {

    public SessaoDTO sessaoFormToSessaoDTO(SessaoForm sessaoForm) {
        return new SessaoDTO(sessaoForm.getPautaId(), sessaoForm.getDataEncerramento());
    }

    public SessaoDTO sessaoFormToSessaoDTO(Sessao sessao) {
        return new SessaoDTO(sessao.getId(), sessao.getPautaId(), sessao.getDataCriacao(), sessao.getDataEncerramento());
    }

    public SessaoDTO alterarFormtoSessaoDTO(SessaoAlterarForm sessaoForm) {
        return new SessaoDTO(sessaoForm.getDataEncerramento());
    }


}
