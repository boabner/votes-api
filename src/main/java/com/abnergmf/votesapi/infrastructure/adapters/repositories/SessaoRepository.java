package com.abnergmf.votesapi.infrastructure.adapters.repositories;

import java.util.Date;
import java.util.List;
import java.util.Objects;
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
        List<Sessao> sessaoList = sessaoRepositoryDAO.findAll().stream().map(sessaoConverter::toSessao).collect((Collectors.toList()));
        return sessaoList;
    }

    @Override
    public List<Sessao> listarSessoesAtivas() {
        List<Sessao> sessaoList =
                sessaoRepositoryDAO.findAllByDataEncerramentoAfter(new Date())
                        .stream().map(sessaoConverter::toSessao).collect((Collectors.toList()));
        return sessaoList;
    }

    @Override
    public Sessao getById(Long idSessao) {

        Optional<SessaoEntity> sessaoEntityOptional = sessaoRepositoryDAO.findById(idSessao);

        if (sessaoEntityOptional.isPresent()) {
            return sessaoConverter.toSessao(sessaoEntityOptional.get());
        }
        else {
            logger.info("Sessao com id " + idSessao + " não encontrado.");
            throw new VoteAPIObjectNotFoundException("Sessao", idSessao);
        }
    }

    @Override
    public Sessao salvar(Sessao sessao) {
        SessaoEntity sessaoEntity;
        Optional<PautaEntity> optionalPautaEntity = pautaRepositoryDAO.findById(sessao.getPautaId());
        if (optionalPautaEntity.isPresent()) {
            sessaoEntity = new SessaoEntity();
            sessaoEntity.setPautaEntity(optionalPautaEntity.get());
            sessaoEntity.setDataCriacao(new Date());
            sessaoEntity.setDataEncerramento(sessao.getDataEncerramento());
        }
        else {
            logger.info("Pauta com id " + sessao.getPautaId() + " não encontrada.");
            throw new VoteAPIObjectNotFoundException("Sessao", sessao.getId());
        }
        sessaoRepositoryDAO.save(sessaoEntity);
        return sessao;
    }

    @Override
    public Sessao atualizar(Sessao sessao) {

        SessaoEntity sessaoEntity;
        if (!Objects.isNull(sessao.getId())) {

            Optional<SessaoEntity> optionalSessao = sessaoRepositoryDAO.findById(sessao.getId());

            if (optionalSessao.isPresent()) {

                sessaoEntity = optionalSessao.get();
                sessaoEntity.atualizar(sessao);
                sessaoRepositoryDAO.save(sessaoEntity);

            }
            else {
                logger.info("Sessao com id " + sessao.getId() + " não encontrado.");
                throw new VoteAPIObjectNotFoundException("Sessao", sessao.getId());
            }
        }
        return sessao;
    }

    @Override
    public Boolean remover(Sessao sessao) {
        SessaoEntity sessaoEntity;
        if (!Objects.isNull(sessao.getId())) {
            Optional<SessaoEntity> optionalSessao = sessaoRepositoryDAO.findById(sessao.getId());
            if (optionalSessao.isPresent()) {
                sessaoEntity = optionalSessao.get();
                sessaoRepositoryDAO.delete(sessaoEntity);
                return true;
            }
            else {
                logger.info(" com id " + sessao.getId() + " não encontrado.");
                throw new VoteAPIObjectNotFoundException("Sessao", sessao.getId());
            }
        }
        return false;
    }
}
