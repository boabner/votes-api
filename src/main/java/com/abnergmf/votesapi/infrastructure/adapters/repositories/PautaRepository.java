package com.abnergmf.votesapi.infrastructure.adapters.repositories;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.abnergmf.votesapi.application.error.VoteAPIObjectNotFoundException;
import com.abnergmf.votesapi.domain.Pauta;
import com.abnergmf.votesapi.domain.ports.repositories.PautaRepositoryPort;
import com.abnergmf.votesapi.infrastructure.adapters.converter.PautaConverter;
import com.abnergmf.votesapi.infrastructure.adapters.entities.PautaEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PautaRepository  implements PautaRepositoryPort {

    private static final Logger logger = LoggerFactory.getLogger(PautaRepository.class.getName());
    private final PautaRepositoryDAO pautaRepositoryDAO;
    private final PautaConverter pautaConverter;

    public PautaRepository(PautaRepositoryDAO pautaRepositoryDAO, PautaConverter pautaConverter) {
        this.pautaRepositoryDAO = pautaRepositoryDAO;
        this.pautaConverter = pautaConverter;
    }

    @Override
    public List<Pauta> listarTodos() {
        List<Pauta> pautaList = pautaRepositoryDAO.findAll().stream().map(pautaConverter::toPauta).collect((Collectors.toList()));
        return pautaList;
    }

    @Override
    public Pauta getById(Long idPauta) {
        Optional<PautaEntity> pautaEntityOptional = pautaRepositoryDAO.findById(idPauta);

        if (pautaEntityOptional.isPresent()) {
            return pautaConverter.toPauta(pautaEntityOptional.get());
        }
        else {
            logger.info("Pauta com id " + idPauta + " não encontrado.");
            throw new VoteAPIObjectNotFoundException("Pauta", idPauta);
        }
    }

    @Override
    public Pauta salvar(Pauta pauta) {

        PautaEntity pautaEntity = pautaConverter.toPautaEntity(pauta);

        pautaRepositoryDAO.save(pautaEntity);

        return pauta;
    }

    @Override
    public Pauta atualizar(Pauta pauta) {
        if (!Objects.isNull(pauta.getId())) {

            PautaEntity pautaEntity;
            Optional<PautaEntity> optionalPauta = pautaRepositoryDAO.findById(pauta.getId());

            if (optionalPauta.isPresent()) {

                pautaEntity = optionalPauta.get();
                pautaEntity.atualizar(pauta);

                pautaRepositoryDAO.save(pautaEntity);
            }
            else {
                logger.info("Pauta com id " + pauta.getId() + " não encontrado.");
                throw new VoteAPIObjectNotFoundException("Pauta", pauta.getId());
            }

        }
        return pauta;
    }

    @Override
    public Boolean remover(Pauta pauta) {
        PautaEntity pautaEntity;
        if (!Objects.isNull(pauta.getId())) {
            Optional<PautaEntity> optionalPauta = pautaRepositoryDAO.findById(pauta.getId());
            if (optionalPauta.isPresent()) {
                pautaEntity = optionalPauta.get();
                pautaRepositoryDAO.delete(pautaEntity);
                return true;
            }
            else {
                logger.info(" com id " + pauta.getId() + " não encontrado.");
                throw new VoteAPIObjectNotFoundException("Pauta", pauta.getId());
            }
        }
        return false;
    }
}
