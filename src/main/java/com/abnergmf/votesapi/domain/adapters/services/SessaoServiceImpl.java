package com.abnergmf.votesapi.domain.adapters.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.abnergmf.votesapi.application.adapters.converter.SessaoAtivaDTOConverter;
import com.abnergmf.votesapi.application.adapters.converter.SessaoDTOConverter;
import com.abnergmf.votesapi.application.util.DateUtil;
import com.abnergmf.votesapi.domain.Sessao;
import com.abnergmf.votesapi.domain.dtos.SessaoAtivaDTO;
import com.abnergmf.votesapi.domain.dtos.SessaoDTO;
import com.abnergmf.votesapi.domain.ports.interfaces.SessaoServicePort;
import com.abnergmf.votesapi.domain.ports.repositories.SessaoRepositoryPort;

public class SessaoServiceImpl implements SessaoServicePort {

    private final SessaoRepositoryPort sessaoRepository;
    private final SessaoDTOConverter sessaoDTOConverter;
    private final SessaoAtivaDTOConverter sessaoAtivaDTOConverter;

    public SessaoServiceImpl(SessaoRepositoryPort sessaoRepository, SessaoDTOConverter sessaoDTOConverter, SessaoAtivaDTOConverter sessaoAtivaDTOConverter) {
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
        Sessao sessao = new Sessao(sessaoDTO);

        sessaoRepository.salvar(sessao);

        return sessao;
    }

    @Override
    public Sessao atualizarSessao(Long id, SessaoDTO sessaoDTO) {

        Sessao sessao = sessaoRepository.getById(id);

        sessao.setDataEncerramento(sessaoDTO.getDataEncerramento());

        sessaoRepository.atualizar(sessao);

        return sessao;
    }

    @Override
    public Boolean removerSessao(Long id)  {

        Sessao sessao = sessaoRepository.getById(id);

        boolean isRemoved = sessaoRepository.remover(sessao);

        return isRemoved;
    }

    @Override
    public List<SessaoDTO> listarSessaos() {
        List<Sessao> listSessaos = sessaoRepository.listarTodos();
        return listSessaos.stream().map(sessaoDTOConverter::sessaoToSessaoDTO).collect(Collectors.toList());
    }

    @Override
    public List<SessaoAtivaDTO> listarSessoesAtivas() {
        List<Sessao> listSessaos = sessaoRepository.listarSessoesAtivas();
        return listSessaos.stream().map(sessaoAtivaDTOConverter::sessaoToSessaoAtivaDTO).collect(Collectors.toList());
    }

}
