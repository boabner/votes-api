package com.abnergmf.votesapi.domain;

import static org.mockito.Mockito.when;

import java.util.Date;

import com.abnergmf.votesapi.application.error.VoteAPIObjectNotFoundException;
import com.abnergmf.votesapi.domain.adapters.services.VotacaoServiceImpl;
import com.abnergmf.votesapi.domain.dtos.VotacaoDTO;
import com.abnergmf.votesapi.infrastructure.adapters.repositories.VotacaoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@SpringBootTest
@RunWith(SpringRunner.class)
public class VotacaoServiceImplTest {

    @Mock
    private VotacaoServiceImpl votacaoServiceImpl;

    @Mock
    private VotacaoRepository votacaoRepository;

    private Long sessaoId;
    private Long votacaoId;
    private String escolha;
    private Votacao votacao;

    @Before
    public void init() {

        sessaoId = 1L;
        votacaoId = 1L;
        escolha = "S";
        votacao = new Votacao(votacaoId, escolha, sessaoId);

        when(votacaoRepository.salvar(votacao)).thenReturn(votacao);
    }

    @Test
    public void deveRetornarVotacaoAoChamarProcessarVotoComEscolhaESessaoIdExistente() {

        VotacaoDTO votacaoDTO = new VotacaoDTO(votacaoId, escolha, sessaoId);

        when(votacaoServiceImpl.processarVoto(votacaoDTO)).thenReturn(votacao);
        Votacao votacaoAberta = votacaoServiceImpl.processarVoto(votacaoDTO);

        Assert.notNull(votacaoAberta);
    }

    @Test(expected = VoteAPIObjectNotFoundException.class)
    public void deveRetornarVoteAPIObjectNotFoundExceptionAoProcessarVotoComSessaoIdInexistente() {

        VotacaoDTO votacaoDTO = new VotacaoDTO(escolha, -1L);

        when(votacaoServiceImpl.processarVoto(votacaoDTO)).thenThrow(new VoteAPIObjectNotFoundException("Votacao", -1L));

        votacaoServiceImpl.processarVoto(votacaoDTO);

    }

}
