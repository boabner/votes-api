package com.abnergmf.votesapi.domain;

import static org.mockito.Mockito.when;

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
    private Long associadoId;
    private String escolha;
    private com.abnergmf.votesapi.domain.votacao votacao;

    @Before
    public void init() {

        sessaoId = 1L;
        votacaoId = 1L;
        associadoId = 1L;

        escolha = "S";

        votacao = new votacao(votacaoId, escolha, sessaoId, associadoId);

        when(votacaoRepository.salvar(votacao)).thenReturn(votacao);
    }

    @Test
    public void deveRetornarVotacaoAoChamarProcessarVotoComEscolhaESessaoIdEAssociadoIdExistente() {

        VotacaoDTO votacaoDTO = new VotacaoDTO(votacaoId, escolha, sessaoId, associadoId);

        when(votacaoServiceImpl.processarVoto(votacaoDTO)).thenReturn(votacao);
        com.abnergmf.votesapi.domain.votacao votacaoAberta = votacaoServiceImpl.processarVoto(votacaoDTO);

        Assert.notNull(votacaoAberta);
    }

    @Test(expected = VoteAPIObjectNotFoundException.class)
    public void deveRetornarVoteAPIObjectNotFoundExceptionAoProcessarVotoComSessaoIdInexistente() {

        VotacaoDTO votacaoDTO = new VotacaoDTO(escolha, -1L, associadoId);

        when(votacaoServiceImpl.processarVoto(votacaoDTO)).thenThrow(new VoteAPIObjectNotFoundException("Votacao", -1L));

        votacaoServiceImpl.processarVoto(votacaoDTO);

    }

    @Test(expected = VoteAPIObjectNotFoundException.class)
    public void deveRetornarVoteAPIObjectNotFoundExceptionAoProcessarVotoComSessaoIdEAssociadoIdInexistente() {

        VotacaoDTO votacaoDTO = new VotacaoDTO(escolha, sessaoId, -1L);

        when(votacaoServiceImpl.processarVoto(votacaoDTO)).thenThrow(new VoteAPIObjectNotFoundException("Votacao", -1L));

        votacaoServiceImpl.processarVoto(votacaoDTO);

    }

}
