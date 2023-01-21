package com.abnergmf.votesapi.infrastructure.adapters.repositories;

import com.abnergmf.votesapi.infrastructure.adapters.entities.VotacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotacaoRepositoryDAO extends JpaRepository<VotacaoEntity, Long> {

}
