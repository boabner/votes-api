package com.abnergmf.votesapi.domain.adapters.services;

import java.util.List;
import java.util.stream.Collectors;

import com.abnergmf.votesapi.application.adapters.converter.PautaDTOConverter;
import com.abnergmf.votesapi.application.error.PautaAlreadyStartedException;
import com.abnergmf.votesapi.domain.Pauta;
import com.abnergmf.votesapi.domain.dtos.PautaDTO;
import com.abnergmf.votesapi.domain.ports.interfaces.PautaServicePort;
import com.abnergmf.votesapi.domain.ports.interfaces.SessaoServicePort;
import com.abnergmf.votesapi.domain.ports.repositories.PautaRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;

public class PautaServiceImpl implements PautaServicePort {

    @Autowired
    private SessaoServicePort sessaoServicePort;
    private final PautaRepositoryPort pautaRepository;
    private final PautaDTOConverter pautaDTOConverter;

    public PautaServiceImpl(PautaRepositoryPort pautaRepository, PautaDTOConverter pautaDTOConverter) {
        this.pautaRepository = pautaRepository;
        this.pautaDTOConverter = pautaDTOConverter;
    }

    @Override
    public Pauta criarPauta(PautaDTO pautaDTO) {

        Pauta pauta = new Pauta(pautaDTO);
        pautaRepository.salvar(pauta);

        return pauta;
    }

    @Override
    public Pauta atualizarPauta(Long id, PautaDTO pautaDTO) {

        Pauta pauta = pautaRepository.getById(id);

        pauta.setNome(pautaDTO.getNome());

        pautaRepository.atualizar(pauta);

        return pauta;
    }

    @Override
    public Boolean processarPedidoDeRemocao(Long id) {
        if (sessaoServicePort.listarSessoesPorPautaId(id).isEmpty()) {
            return removerPauta(id);
        }
        throw new PautaAlreadyStartedException(id);
    }

    private Boolean removerPauta(Long id)  {

        Pauta pauta = pautaRepository.getById(id);

        return pautaRepository.remover(pauta);
    }

    @Override
    public List<PautaDTO> listarPautas() {
        List<Pauta> listPautas = pautaRepository.listarTodos();
        return listPautas.stream().map(pautaDTOConverter::toPautaDTO).collect(Collectors.toList());
    }

}
