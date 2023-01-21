package com.abnergmf.votesapi.infrastructure.adapters.repositories;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.abnergmf.votesapi.application.error.VoteAPIObjectNotFoundException;
import com.abnergmf.votesapi.domain.Votacao;
import com.abnergmf.votesapi.domain.ports.repositories.VotacaoRepositoryPort;
import com.abnergmf.votesapi.infrastructure.adapters.converter.VotacaoConverter;
import com.abnergmf.votesapi.infrastructure.adapters.entities.SessaoEntity;
import com.abnergmf.votesapi.infrastructure.adapters.entities.VotacaoEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VotacaoRepository implements VotacaoRepositoryPort {

    private static final Logger logger = LoggerFactory.getLogger(VotacaoRepository.class.getName());

    @Autowired
    private VotacaoRepositoryDAO votacaoRepositoryDAO;
    @Autowired
    private VotacaoConverter votacaoConverter;
    @Autowired
    private SessaoRepositoryDAO sessaoRepositoryDAO;

    @Override
    public List<Votacao> listarTodosPorSessaoId(Long sessaoId) {
        return votacaoRepositoryDAO.findAll().stream().map(votacaoConverter::toVotacao).collect((Collectors.toList()));
    }

    @Override
    public Votacao salvar(Votacao votacao) {

        Optional<SessaoEntity> optionalSessaoEntity = sessaoRepositoryDAO.findById(votacao.getSessaoId());
        if (optionalSessaoEntity.isPresent()) {

            VotacaoEntity votacaoEntity = votacaoConverter.toVotacaoEntity(votacao, optionalSessaoEntity.get());
            votacaoRepositoryDAO.save(votacaoEntity);

            return votacao;
        }
        else {
            logger.info("Sessão com id " + votacao.getSessaoId() + " não encontrada.");
            throw new VoteAPIObjectNotFoundException("Sessão", votacao.getSessaoId());
        }
    }
}
