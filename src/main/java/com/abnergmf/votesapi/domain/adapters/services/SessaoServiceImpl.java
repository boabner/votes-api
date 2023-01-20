package com.abnergmf.votesapi.domain.adapters.services;

import java.util.List;
import java.util.stream.Collectors;

import com.abnergmf.votesapi.application.adapters.controller.SessaoController;
import com.abnergmf.votesapi.application.adapters.converter.SessaoAtivaDTOConverter;
import com.abnergmf.votesapi.application.adapters.converter.SessaoDTOConverter;
import com.abnergmf.votesapi.domain.Sessao;
import com.abnergmf.votesapi.domain.dtos.SessaoAtivaDTO;
import com.abnergmf.votesapi.domain.dtos.SessaoDTO;
import com.abnergmf.votesapi.domain.ports.interfaces.SessaoServicePort;
import com.abnergmf.votesapi.domain.ports.repositories.SessaoRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessaoServiceImpl implements SessaoServicePort {

    private static final Logger logger = LoggerFactory.getLogger(SessaoController.class.getName());
    private final SessaoRepositoryPort sessaoRepository;
    private final SessaoDTOConverter sessaoDTOConverter;
    private final SessaoAtivaDTOConverter sessaoAtivaDTOConverter;

    public SessaoServiceImpl(SessaoRepositoryPort sessaoRepository, SessaoDTOConverter sessaoDTOConverter, SessaoAtivaDTOConverter sessaoAtivaDTOConverter) {
        this.sessaoRepository = sessaoRepository;
        this.sessaoDTOConverter = sessaoDTOConverter;
        this.sessaoAtivaDTOConverter = sessaoAtivaDTOConverter;
    }

    @Override
    public Sessao criarSessao(SessaoDTO sessaoDTO) {
        Sessao sessao = new Sessao(sessaoDTO);
        sessaoRepository.persistir(sessao);

        logger.info("Sessao \"" + sessao.getId() + "\" criada na base.");

        return sessao;
    }

    @Override
    public Sessao atualizarSessao(Long id, SessaoDTO sessaoDTO) {

        Sessao sessao = sessaoRepository.getById(id);

        sessao.setDataEncerramento(sessaoDTO.getDataEncerramento());

        sessaoRepository.persistir(sessao);

        logger.info("Sessao \"" + id + "\" atualizada na base.");

        return sessao;
    }

    @Override
    public Boolean removerSessao(Long id)  {

        Sessao sessao = sessaoRepository.getById(id);

        boolean isRemoved = sessaoRepository.remover(sessao);

        logger.info("Sessao \"" + id + "\" removida da base.");

        return isRemoved;
    }

    @Override
    public List<SessaoDTO> listarSessaos() {
        List<Sessao> listSessaos = sessaoRepository.listarTodos();
        return listSessaos.stream().map(sessaoDTOConverter::toSessaoDTO).collect(Collectors.toList());
    }

    @Override
    public List<SessaoAtivaDTO> listarSessoesAtivas() {
        List<Sessao> listSessaos = sessaoRepository.listarSessoesAtivas();
        return listSessaos.stream().map(sessaoAtivaDTOConverter::toSessaoAtivaDTO).collect(Collectors.toList());
    }

}
