package com.abnergmf.votesapi.domain.adapters.services;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.abnergmf.votesapi.domain.Pauta;
import com.abnergmf.votesapi.domain.dtos.PautaDTO;
import com.abnergmf.votesapi.domain.ports.interfaces.PautaServicePort;
import com.abnergmf.votesapi.domain.ports.repositories.PautaRepositoryPort;

public class PautaServiceImpl implements PautaServicePort {

    private final PautaRepositoryPort pautaRepository;

    public PautaServiceImpl(PautaRepositoryPort pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    @Override
    public List<PautaDTO> listarPautas() {
        List<Pauta> listPautas = pautaRepository.listarTodos();
        return listPautas.stream().map(Pauta::toPautaDTO).collect(Collectors.toList());
    }

    @Override
    public void criarPauta(PautaDTO pautaDTO) {
        pautaRepository.salvar(new Pauta(pautaDTO));
    }

    @Override
    public void atualizarPauta(Long idPauta, PautaDTO pautaDTO) throws Exception {

        Pauta pauta = pautaRepository.getById(idPauta);

        if (Objects.isNull(pauta)) {
            throw new Exception();
        }
        pautaRepository.salvar(pauta);
    }

    @Override
    public void removerPauta(Long idPauta) throws Exception {
        Pauta pauta = pautaRepository.getById(idPauta);

        if (Objects.isNull(pauta)) {
            throw new Exception();
        }
        pautaRepository.remover(pauta);
    }
}
