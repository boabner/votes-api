package com.abnergmf.votesapi.infrastructure.adapters.repositories;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.abnergmf.votesapi.application.error.EntityNotFoundException;
import com.abnergmf.votesapi.domain.Pauta;
import com.abnergmf.votesapi.domain.ports.repositories.PautaRepositoryPort;
import com.abnergmf.votesapi.infrastructure.adapters.entities.PautaEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PautaRepository  implements PautaRepositoryPort {

    private static final Logger logger = LoggerFactory.getLogger(PautaRepository.class.getName());
    private final PautaRepositoryDAO pautaRepositoryDAO;

    public PautaRepository(PautaRepositoryDAO pautaRepositoryDAO) {
        this.pautaRepositoryDAO = pautaRepositoryDAO;
    }

    @Override
    public List<Pauta> listarTodos() {
        return pautaRepositoryDAO.findAll().stream().map(PautaEntity::toPauta).collect((Collectors.toList()));
    }

    @Override
    public Pauta getById(Long idPauta) throws Exception {
        Optional<PautaEntity> pautaEntityOptional = pautaRepositoryDAO.findById(idPauta);

        if (pautaEntityOptional.isPresent()) {
            return pautaEntityOptional.get().toPauta();
        }
        else {
            logger.info("Pauta com id " + idPauta + " não encontrado.");
            throw new EntityNotFoundException("Pauta", idPauta);
        }
    }

    @Override
    public void salvar(Pauta pauta) {
        PautaEntity pautaEntity;
        if (!Objects.isNull(pauta.getId())) {
            Optional<PautaEntity> optionalPauta = pautaRepositoryDAO.findById(pauta.getId());
            if (optionalPauta.isPresent()) {
                pautaEntity = optionalPauta.get();
                pautaEntity.atualizar(pauta);
            }
            else {
                logger.info(" com id " + pauta.getId() + " não encontrado.");
                throw new EntityNotFoundException("Pauta", pauta.getId());
            }
        }
        else {
            pautaEntity = new PautaEntity(pauta);
        }

        pautaRepositoryDAO.save(pautaEntity);
    }

    @Override
    public void remover(Pauta pauta) {
        PautaEntity pautaEntity;
        if (!Objects.isNull(pauta.getId())) {
            Optional<PautaEntity> optionalPauta = pautaRepositoryDAO.findById(pauta.getId());
            if (optionalPauta.isPresent()) {
                pautaEntity = optionalPauta.get();
                pautaRepositoryDAO.delete(pautaEntity);
            }
            else {
                logger.info(" com id " + pauta.getId() + " não encontrado.");
                throw new EntityNotFoundException("Pauta", pauta.getId());
            }
        }
    }
}
