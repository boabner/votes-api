package com.abnergmf.votesapi.domain.adapters.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.abnergmf.votesapi.application.adapters.converter.SessaoResultadoDTOConverter;
import com.abnergmf.votesapi.application.adapters.converter.SessaoDTOConverter;
import com.abnergmf.votesapi.application.util.DateUtil;
import com.abnergmf.votesapi.domain.Sessao;
import com.abnergmf.votesapi.domain.dtos.SessaoResultadoDTO;
import com.abnergmf.votesapi.domain.dtos.SessaoDTO;
import com.abnergmf.votesapi.domain.ports.interfaces.SessaoServicePort;
import com.abnergmf.votesapi.domain.ports.repositories.SessaoRepositoryPort;
import com.abnergmf.votesapi.infrastructure.adapters.repositories.PautaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessaoServiceImpl implements SessaoServicePort {

    private static final Logger logger = LoggerFactory.getLogger(PautaRepository.class.getName());
    private final SessaoRepositoryPort sessaoRepository;
    private final SessaoDTOConverter sessaoDTOConverter;
    private final SessaoResultadoDTOConverter sessaoAtivaDTOConverter;

    public SessaoServiceImpl(SessaoRepositoryPort sessaoRepository, SessaoDTOConverter sessaoDTOConverter, SessaoResultadoDTOConverter sessaoAtivaDTOConverter) {
        this.sessaoRepository = sessaoRepository;
        this.sessaoDTOConverter = sessaoDTOConverter;
        this.sessaoAtivaDTOConverter = sessaoAtivaDTOConverter;
    }

    @Override
    public SessaoDTO prepararAberturaSessao(SessaoDTO sessaoDTO) {
        if (sessaoDTO.getDataEncerramento() == null) {
            sessaoDTO.setDataEncerramento(DateUtil.acrescentarMinutosNaData(new Date(), Sessao.TEMPO_SESSAO_ABERTA_DEFAULT));
        }
        return sessaoDTO;
    }

    @Override
    public Sessao abrirSessao(SessaoDTO sessaoDTO) {

        try {

            Sessao sessao = new Sessao(sessaoDTO);
            sessaoRepository.salvar(sessao);
            return sessao;

        } finally {
            logger.info("Sessão para a pauta " + sessaoDTO.getPautaId() + " aberta com sucesso em " + DateUtil.converterDataEmString(new Date()));
        }

    }

    @Override
    public Boolean validarSessaoAntesDeProsseguir(Long sessaoId) {

        Sessao sessao = sessaoRepository.getById(sessaoId);

        return sessao.getDataEncerramento().compareTo(new Date()) != -1;
    }

    @Override
    public List<SessaoResultadoDTO> listarSessaos() {
        List<Sessao> listSessaos = sessaoRepository.listarTodos();
        return listSessaos.stream().map(sessaoAtivaDTOConverter::sessaoToSessaoResultadoDTO).collect(Collectors.toList());
    }

    @Override
    public List<SessaoResultadoDTO> listarSessoesAtivas() {
        List<Sessao> listSessaos = sessaoRepository.listarSessoesAtivas();
        return listSessaos.stream().map(sessaoAtivaDTOConverter::sessaoToSessaoResultadoDTO).collect(Collectors.toList());
    }

    @Override
    public List<SessaoDTO> listarSessoesPorPautaId(Long pautaId) {
        List<Sessao> listSessaos = sessaoRepository.listarSessoesPorPautaId(pautaId);
        return listSessaos.stream().map(sessaoDTOConverter::sessaoToSessaoDTO).collect(Collectors.toList());
    }

}
