package com.abnergmf.votesapi.infrastructure.adapters.converter;

import com.abnergmf.votesapi.domain.Sessao;
import com.abnergmf.votesapi.domain.ports.interfaces.PautaServicePort;
import com.abnergmf.votesapi.domain.ports.repositories.PautaRepositoryPort;
import com.abnergmf.votesapi.domain.ports.repositories.SessaoRepositoryPort;
import com.abnergmf.votesapi.infrastructure.adapters.entities.SessaoEntity;
import com.abnergmf.votesapi.infrastructure.adapters.repositories.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessaoConverter {

    @Autowired
    private PautaRepositoryPort pautaRepositoryPort;

    public Sessao toSessao(SessaoEntity sessaoEntity) {
        return new Sessao(sessaoEntity.getId(), sessaoEntity.getDataCriacao(), sessaoEntity.getDataEncerramento(), sessaoEntity.getPautaEntity().getId());
    }

}
