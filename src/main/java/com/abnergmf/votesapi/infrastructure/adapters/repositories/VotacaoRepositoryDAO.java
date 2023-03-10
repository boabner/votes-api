package com.abnergmf.votesapi.infrastructure.adapters.repositories;

import java.util.List;
import java.util.Optional;

import com.abnergmf.votesapi.infrastructure.adapters.entities.VotacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotacaoRepositoryDAO extends JpaRepository<VotacaoEntity, Long> {
    List<VotacaoEntity> findAllBySessaoEntityId(Long sessaoId);
    VotacaoEntity findByAssociadoIdAndSessaoEntityId(Long associadoId, Long sessaoId);
}
