package com.abnergmf.votesapi.application.adapters.converter;

import com.abnergmf.votesapi.application.adapters.controller.form.PautaForm;
import com.abnergmf.votesapi.domain.Pauta;
import com.abnergmf.votesapi.domain.dtos.PautaDTO;
import org.springframework.stereotype.Component;

@Component
public class PautaDTOConverter {

    public PautaDTO toPautaDTO(PautaForm pautaForm) {
        return new PautaDTO(pautaForm.getNome());
    }

    public PautaDTO toPautaDTO(Pauta pauta) {
        return new PautaDTO(pauta.getId(), pauta.getNome());
    }
}
