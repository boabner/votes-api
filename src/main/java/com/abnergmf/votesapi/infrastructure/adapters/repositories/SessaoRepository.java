package com.abnergmf.votesapi.infrastructure.adapters.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.abnergmf.votesapi.application.error.VoteAPIObjectNotFoundException;
import com.abnergmf.votesapi.domain.Sessao;
import com.abnergmf.votesapi.domain.ports.repositories.SessaoRepositoryPort;
import com.abnergmf.votesapi.infrastructure.adapters.converter.SessaoConverter;
import com.abnergmf.votesapi.infrastructure.adapters.entities.PautaEntity;
import com.abnergmf.votesapi.infrastructure.adapters.entities.SessaoEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessaoRepository implements SessaoRepositoryPort {

    private static final Logger logger = LoggerFactory.getLogger(SessaoRepository.class.getName());

    @Autowired
    private SessaoRepositoryDAO sessaoRepositoryDAO;
    @Autowired
    private PautaRepositoryDAO pautaRepositoryDAO;
    @Autowired
    private SessaoConverter sessaoConverter;

    @Override
    public List<Sessao> listarTodos() {
        return sessaoRepositoryDAO.findAll().stream().map(sessaoConverter::toSessao).collect((Collectors.toList()));
    }

    @Override
    public List<Sessao> listarSessoesAtivas() {
        return sessaoRepositoryDAO.findAllByDataEncerramentoAfter(new Date())
                .stream().map(sessaoConverter::toSessao).collect((Collectors.toList()));
    }

    @Override
    public Sessao buscarSessaoPorPautaId(Long pautaId) {
        SessaoEntity sessaoEntity = sessaoRepositoryDAO.findByPautaEntityId(pautaId);
        return sessaoEntity != null ? sessaoConverter.toSessao(sessaoEntity) : null;
    }

    @Override
    public Sessao getById(Long idSessao) {

        Optional<SessaoEntity> sessaoEntityOptional = sessaoRepositoryDAO.findById(idSessao);

        if (sessaoEntityOptional.isPresent()) {
            return sessaoConverter.toSessao(sessaoEntityOptional.get());
        }
        else {
            throw new VoteAPIObjectNotFoundException("Sessao", idSessao);
        }
    }

    @Override
    public Sessao salvar(Sessao sessao) {

        Optional<PautaEntity> optionalPautaEntity = pautaRepositoryDAO.findById(sessao.getPautaId());
        if (optionalPautaEntity.isPresent()) {

            SessaoEntity sessaoEntity = sessaoConverter.toSessaoEntity(sessao, optionalPautaEntity.get());

            sessaoRepositoryDAO.save(sessaoEntity);
            return sessao;
        }
        else {
            throw new VoteAPIObjectNotFoundException("Pauta", sessao.getPautaId());
        }
    }

}
