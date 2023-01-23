package com.abnergmf.votesapi.infrastructure.adapters.converter;

import com.abnergmf.votesapi.domain.votacao;
import com.abnergmf.votesapi.infrastructure.adapters.entities.SessaoEntity;
import com.abnergmf.votesapi.infrastructure.adapters.entities.VotacaoEntity;
import org.springframework.stereotype.Component;

@Component
public class VotacaoConverter {

    public votacao toVotacao(VotacaoEntity votacaoEntity) {
        return new votacao(votacaoEntity.getId(), votacaoEntity.getEscolha(), votacaoEntity.getSessaoEntity().getId(), votacaoEntity.getAssociadoId());
    }

    public VotacaoEntity toVotacaoEntity(votacao votacao, SessaoEntity sessaoEntity) {
        return new VotacaoEntity(votacao.getId(), votacao.getEscolha(), sessaoEntity, votacao.getAssociadoId());
    }

}
