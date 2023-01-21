package com.abnergmf.votesapi.domain.adapters.services;

import java.util.List;
import java.util.stream.Collectors;

import com.abnergmf.votesapi.application.adapters.converter.VotacaoDTOConverter;
import com.abnergmf.votesapi.domain.Votacao;
import com.abnergmf.votesapi.domain.dtos.VotacaoDTO;
import com.abnergmf.votesapi.domain.ports.interfaces.VotacaoServicePort;
import com.abnergmf.votesapi.domain.ports.repositories.VotacaoRepositoryPort;

public class VotacaoServiceImpl implements VotacaoServicePort {

    private final VotacaoRepositoryPort votacaoRepository;
    private final VotacaoDTOConverter votacaoDTOConverter;

    public VotacaoServiceImpl(VotacaoRepositoryPort votacaoRepository, VotacaoDTOConverter votacaoDTOConverter) {
        this.votacaoRepository = votacaoRepository;
        this.votacaoDTOConverter = votacaoDTOConverter;
    }

    @Override
    public Votacao registrarVoto(VotacaoDTO votacaoDTO) {

        Votacao votacao = new Votacao(votacaoDTO);
        votacaoRepository.salvar(votacao);

        return votacao;
    }

    @Override
    public List<VotacaoDTO> listarVotosPorSessaoId(Long sessaoId) {
        List<Votacao> listVotacaos = votacaoRepository.listarTodosPorSessaoId(sessaoId);
        return listVotacaos.stream().map(votacaoDTOConverter::toVotacaoDTO).collect(Collectors.toList());
    }

}
