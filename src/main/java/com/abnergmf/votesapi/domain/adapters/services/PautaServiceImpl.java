package com.abnergmf.votesapi.domain.adapters.services;

import java.util.List;
import java.util.stream.Collectors;

import com.abnergmf.votesapi.application.adapters.controller.PautaController;
import com.abnergmf.votesapi.application.adapters.converter.PautaDTOConverter;
import com.abnergmf.votesapi.domain.Pauta;
import com.abnergmf.votesapi.domain.dtos.PautaDTO;
import com.abnergmf.votesapi.domain.ports.interfaces.PautaServicePort;
import com.abnergmf.votesapi.domain.ports.repositories.PautaRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PautaServiceImpl implements PautaServicePort {

    private static final Logger logger = LoggerFactory.getLogger(PautaController.class.getName());
    private final PautaRepositoryPort pautaRepository;
    private final PautaDTOConverter pautaDTOConverter;

    public PautaServiceImpl(PautaRepositoryPort pautaRepository, PautaDTOConverter pautaDTOConverter) {
        this.pautaRepository = pautaRepository;
        this.pautaDTOConverter = pautaDTOConverter;
    }

    @Override
    public Pauta criarPauta(PautaDTO pautaDTO) {
        Pauta pauta = new Pauta(pautaDTO);
        pautaRepository.persistir(pauta);

        logger.info("Pauta \"" + pauta.getNome() + "\" criada na base.");

        return pauta;
    }

    @Override
    public Pauta atualizarPauta(Long id, PautaDTO pautaDTO) {

        Pauta pauta = pautaRepository.getById(id);

        pauta.setNome(pautaDTO.getNome());

        pautaRepository.persistir(pauta);

        logger.info("Pauta \"" + id + "\" atualizada na base.");

        return pauta;
    }

    @Override
    public Boolean removerPauta(Long id)  {

        Pauta pauta = pautaRepository.getById(id);

        boolean isRemoved = pautaRepository.remover(pauta);

        logger.info("Pauta \"" + id + "\" removida da base.");

        return isRemoved;
    }

    @Override
    public List<PautaDTO> listarPautas() {
        List<Pauta> listPautas = pautaRepository.listarTodos();
        return listPautas.stream().map(pautaDTOConverter::toPautaDTO).collect(Collectors.toList());
    }

}
