package com.abnergmf.votesapi.infrastructure.adapters.repositories;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.abnergmf.votesapi.application.error.MemberDoubleVoteAttemptException;
import com.abnergmf.votesapi.application.error.VoteAPIObjectNotFoundException;
import com.abnergmf.votesapi.domain.dtos.VotacaoDTO;
import com.abnergmf.votesapi.domain.votacao;
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
    public List<votacao> listarTodosPorSessaoId(Long sessaoId) {
        return votacaoRepositoryDAO.findAllBySessaoEntityId(sessaoId).stream().map(votacaoConverter::toVotacao).collect((Collectors.toList()));
    }

    @Override
    public votacao salvar(votacao votacao) {

        Optional<SessaoEntity> optionalSessaoEntity = sessaoRepositoryDAO.findById(votacao.getSessaoId());
        if (optionalSessaoEntity.isPresent()) {

            VotacaoEntity votacaoEntity = votacaoConverter.toVotacaoEntity(votacao, optionalSessaoEntity.get());
            votacaoRepositoryDAO.save(votacaoEntity);

            return votacao;
        }
        else {
            throw new VoteAPIObjectNotFoundException("Sessão", votacao.getSessaoId());
        }
    }

    @Override
    public boolean validarVotoValidoPorAssociadoIdESessaoId(VotacaoDTO votacaoDTO) {
        if (votacaoRepositoryDAO.findByAssociadoIdAndSessaoEntityId(votacaoDTO.getAssociadoId(), votacaoDTO.getSessaoId()) == null) {
            return true;
        }
        throw new MemberDoubleVoteAttemptException(votacaoDTO.getAssociadoId());
    }

}
