package com.abnergmf.votesapi.domain;

import static org.mockito.Mockito.when;

import java.util.Date;

import com.abnergmf.votesapi.application.error.VoteAPIObjectNotFoundException;
import com.abnergmf.votesapi.application.util.DateUtil;
import com.abnergmf.votesapi.domain.adapters.services.SessaoServiceImpl;
import com.abnergmf.votesapi.domain.dtos.SessaoDTO;
import com.abnergmf.votesapi.infrastructure.adapters.repositories.SessaoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SessaoServiceImplTest {

    @Mock
    private SessaoServiceImpl sessaoServiceImpl;

    @Mock
    private SessaoRepository sessaoRepository;

    private Long pautaId;
    private Long sessaoId;
    private Date dataEncerramento;
    private Sessao sessao;

    @Before
    public void init() {

        pautaId = 1L;
        sessaoId = 1L;
        dataEncerramento = DateUtil.acrescentarMinutosNaData(new Date(), 30);

        sessao = new Sessao(sessaoId, new Date(), dataEncerramento, pautaId);

        when(sessaoRepository.salvar(sessao)).thenReturn(sessao);
    }

    @Test
    public void deveRetornarSessaoAoChamarAbrirSessaoComPautaIdEDataEncerramentoExistentes() {

        SessaoDTO sessaoDTO = new SessaoDTO(pautaId, dataEncerramento);

        when(sessaoServiceImpl.abrirSessao(sessaoDTO)).thenReturn(sessao);
        Sessao sessaoAberta = sessaoServiceImpl.abrirSessao(sessaoDTO);

        Assert.notNull(sessaoAberta);
    }

    @Test
    public void deveRetornarSessaoAoChamarAbrirSessaoComPautaIdESemDataEncerramento() {

        SessaoDTO sessaoDTO = new SessaoDTO(pautaId, null);

        when(sessaoServiceImpl.abrirSessao(sessaoDTO)).thenReturn(sessao);
        Sessao sessaoAberta = sessaoServiceImpl.abrirSessao(sessaoDTO);

        Assert.notNull(sessaoAberta);
    }

    @Test(expected = VoteAPIObjectNotFoundException.class)
    public void deveRetornarVoteAPIObjectNotFoundExceptionAoAbrirSessaoComPautaIdInexistente() {

        SessaoDTO sessaoDTO = new SessaoDTO(-1L, dataEncerramento);

        when(sessaoServiceImpl.abrirSessao(sessaoDTO)).thenThrow(new VoteAPIObjectNotFoundException("Sessao", -1L));

        sessaoServiceImpl.abrirSessao(sessaoDTO);

    }

}
