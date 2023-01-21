package com.abnergmf.votesapi.domain.adapters.services;

import java.util.List;

import com.abnergmf.votesapi.application.adapters.converter.VotacaoDTOConverter;
import com.abnergmf.votesapi.domain.Votacao;
import com.abnergmf.votesapi.domain.dtos.ResultadoVotacaoDTO;
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
    public void registrarVoto(VotacaoDTO votacaoDTO) {

        Votacao votacao = new Votacao(votacaoDTO);
        votacaoRepository.salvar(votacao);
    }

    @Override
    public ResultadoVotacaoDTO exibirResultadoPorSessaoId(Long sessaoId) {

        List<Votacao> votacaoList = listarVotosPorSessionId(sessaoId);
        if (!votacaoList.isEmpty()) {

            ResultadoVotacaoDTO resultadoVotacaoDTO = new ResultadoVotacaoDTO(sessaoId);

            votacaoList.forEach(votacao -> {
                if (votacao.getEscolha().equals("S")) {
                    resultadoVotacaoDTO.somarVotoSim();
                } else {
                    resultadoVotacaoDTO.somarVotoNao();
                }
            });

            resultadoVotacaoDTO.registrarResultado(montarResultadoFinal(resultadoVotacaoDTO));

            return resultadoVotacaoDTO;
        }
        return new ResultadoVotacaoDTO(sessaoId, "Sessão em andamento.");
    }

    private String montarResultadoFinal(ResultadoVotacaoDTO resultadoVotacaoDTO) {

        int quantidadeVotosSim = resultadoVotacaoDTO.getQuantidadeVotosSim();
        int quantidadeVotosNao = resultadoVotacaoDTO.getQuantidadeVotosNao();

        String opcaoVencedora = quantidadeVotosNao > quantidadeVotosSim ? "Não" : "Sim";

        String resultado = "Conclui-se que a opção '" + opcaoVencedora + "' foi a opção vencedora.";

        return resultado;
    }

    private List<Votacao> listarVotosPorSessionId(Long sessaoId) {
        return votacaoRepository.listarTodosPorSessaoId(sessaoId);
    }

}
