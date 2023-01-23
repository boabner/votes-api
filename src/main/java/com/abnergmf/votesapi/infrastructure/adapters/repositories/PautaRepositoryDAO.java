package com.abnergmf.votesapi.infrastructure.adapters.repositories;

import java.util.Optional;

import com.abnergmf.votesapi.infrastructure.adapters.entities.PautaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepositoryDAO extends JpaRepository<PautaEntity, Long> {

    @Override
    Optional<PautaEntity> findById(Long id);
}
