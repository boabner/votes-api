package com.abnergmf.votesapi.domain.adapters.services;

import java.util.List;

import com.abnergmf.votesapi.application.error.SessaoEncerradaOuNaoEncontradaException;
import com.abnergmf.votesapi.domain.Votacao;
import com.abnergmf.votesapi.domain.dtos.ResultadoVotacaoDTO;
import com.abnergmf.votesapi.domain.dtos.VotacaoDTO;
import com.abnergmf.votesapi.domain.ports.interfaces.SessaoServicePort;
import com.abnergmf.votesapi.domain.ports.interfaces.VotacaoServicePort;
import com.abnergmf.votesapi.domain.ports.repositories.VotacaoRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;

public class VotacaoServiceImpl implements VotacaoServicePort {

    @Autowired
    private SessaoServicePort sessaoServicePort;
    private final VotacaoRepositoryPort votacaoRepository;

    public VotacaoServiceImpl(VotacaoRepositoryPort votacaoRepository) {
        this.votacaoRepository = votacaoRepository;
    }

    @Override
    public Votacao processarVoto(VotacaoDTO votacaoDTO) {

        if (sessaoServicePort.validarSessaoAntesDeProsseguir(votacaoDTO.getSessaoId())) {
            return registrarVoto(votacaoDTO);
        }
        else {
            throw new SessaoEncerradaOuNaoEncontradaException(votacaoDTO.getSessaoId());
        }

    }

    private Votacao registrarVoto(VotacaoDTO votacaoDTO) {

        Votacao votacao = new Votacao(votacaoDTO);
        votacaoRepository.salvar(votacao);

        return votacao;
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
        if (sessaoServicePort.validarSessaoAntesDeProsseguir(sessaoId)) {
            return votacaoRepository.listarTodosPorSessaoId(sessaoId);
        }
        throw new SessaoEncerradaOuNaoEncontradaException(sessaoId);
    }

}
