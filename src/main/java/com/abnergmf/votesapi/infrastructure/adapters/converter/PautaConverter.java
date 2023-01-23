package com.abnergmf.votesapi.infrastructure.adapters.converter;

import com.abnergmf.votesapi.domain.Pauta;
import com.abnergmf.votesapi.infrastructure.adapters.entities.PautaEntity;
import org.springframework.stereotype.Component;

@Component
public class PautaConverter {

    public Pauta toPauta(PautaEntity pautaEntity) {
        return new Pauta(pautaEntity.getId(), pautaEntity.getNome());
    }

    public PautaEntity toPautaEntity(Pauta pauta) {
        return new PautaEntity(pauta.getId(), pauta.getNome());
    }

}
