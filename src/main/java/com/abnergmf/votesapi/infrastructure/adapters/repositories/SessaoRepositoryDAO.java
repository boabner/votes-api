package com.abnergmf.votesapi.infrastructure.adapters.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.abnergmf.votesapi.infrastructure.adapters.entities.SessaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessaoRepositoryDAO extends JpaRepository<SessaoEntity, Long> {

    @Override
    Optional<SessaoEntity> findById(Long id);

    List<SessaoEntity> findAllByDataEncerramentoAfter(Date dataAtual);

}
