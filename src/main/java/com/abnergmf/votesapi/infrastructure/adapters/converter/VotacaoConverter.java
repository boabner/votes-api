package com.abnergmf.votesapi.infrastructure.adapters.converter;

import com.abnergmf.votesapi.domain.Votacao;
import com.abnergmf.votesapi.infrastructure.adapters.entities.SessaoEntity;
import com.abnergmf.votesapi.infrastructure.adapters.entities.VotacaoEntity;
import org.springframework.stereotype.Component;

@Component
public class VotacaoConverter {

    public Votacao toVotacao(VotacaoEntity votacaoEntity) {
        return new Votacao(votacaoEntity.getId(), votacaoEntity.getEscolha(), votacaoEntity.getSessaoEntity().getId());
    }

    public VotacaoEntity toVotacaoEntity(Votacao votacao, SessaoEntity sessaoEntity) {
        return new VotacaoEntity(votacao.getId(), votacao.getEscolha(), sessaoEntity);
    }

}
