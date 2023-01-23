package com.abnergmf.votesapi.infrastructure.adapters.converter;

import java.util.Date;

import com.abnergmf.votesapi.domain.Sessao;
import com.abnergmf.votesapi.infrastructure.adapters.entities.PautaEntity;
import com.abnergmf.votesapi.infrastructure.adapters.entities.SessaoEntity;
import org.springframework.stereotype.Component;

@Component
public class SessaoConverter {

    public Sessao toSessao(SessaoEntity sessaoEntity) {
        return new Sessao(sessaoEntity.getId(), sessaoEntity.getDataCriacao(), sessaoEntity.getDataEncerramento(), sessaoEntity.getPautaEntity().getId());
    }

    public SessaoEntity toSessaoEntity(Sessao sessao, PautaEntity pautaEntity) {
        return new SessaoEntity(new Date(), sessao.getDataEncerramento(), pautaEntity);
    }
}