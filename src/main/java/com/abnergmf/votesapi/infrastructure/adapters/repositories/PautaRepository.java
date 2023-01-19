package com.abnergmf.votesapi.infrastructure.adapters.repositories;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.abnergmf.votesapi.application.error.UserNotFoundException;
import com.abnergmf.votesapi.domain.Pauta;
import com.abnergmf.votesapi.domain.ports.repositories.PautaRepositoryPort;
import com.abnergmf.votesapi.infrastructure.adapters.entities.PautaEntity;
import org.springframework.stereotype.Component;

@Component
public class PautaRepository  implements PautaRepositoryPort {

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
        throw new UserNotFoundException(idPauta);
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
                return;
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
                pautaEntity = pautaRepositoryDAO.findById(pauta.getId()).get();
                pautaRepositoryDAO.delete(pautaEntity);
            }
            throw new UserNotFoundException(pauta.getId());
        }
    }
}
