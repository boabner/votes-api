package com.abnergmf.votesapi.infrastructure.adapters.repositories;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import com.abnergmf.votesapi.application.error.VoteAPIObjectNotFoundException;
import com.abnergmf.votesapi.application.util.DateUtil;
import com.abnergmf.votesapi.domain.votacao;
import com.abnergmf.votesapi.infrastructure.adapters.converter.VotacaoConverter;
import com.abnergmf.votesapi.infrastructure.adapters.entities.PautaEntity;
import com.abnergmf.votesapi.infrastructure.adapters.entities.SessaoEntity;
import com.abnergmf.votesapi.infrastructure.adapters.entities.VotacaoEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class VotacaoRepositoryTest {

    @InjectMocks
    private VotacaoRepository votacaoRepository;
    @Mock
    private VotacaoConverter votacaoConverter;

    @Mock
    private VotacaoRepositoryDAO votacaoRepositoryDAO;

    @Mock
    private SessaoRepositoryDAO sessaoRepositoryDAO;

    private Long votacaoId;
    private Long sessaoId;
    private Long associadoId;
    private String escolha;
    @Before
    public void init() {

        votacaoId = 1L;
        sessaoId = 1L;
        associadoId = 1L;

        escolha = "S";

        Long pautaId = 1L;

        Date dataEncerramento = DateUtil.acrescentarMinutosNaData(new Date(), 30);
        PautaEntity pautaEntity = new PautaEntity(1L, "Pauta para teste");

        votacao votacaoMock = new votacao(votacaoId, escolha, sessaoId, associadoId);
        SessaoEntity sessaoEntity = new SessaoEntity(sessaoId, new Date(), dataEncerramento, pautaEntity);

        VotacaoEntity votacaoEntityMock = mock(VotacaoEntity.class);
        VotacaoEntity votacaoEntity = new VotacaoEntity(votacaoId, escolha, sessaoEntity, associadoId);

        SessaoEntity sessaoEntityMock = mock(SessaoEntity.class);

        when(votacaoRepositoryDAO.findById(votacaoId)).thenReturn(Optional.of(votacaoEntityMock));

        when(votacaoConverter.toVotacao(votacaoEntity)).thenReturn(votacaoMock);

        votacao votacao = votacaoConverter.toVotacao(votacaoEntity);

        when(sessaoRepositoryDAO.findById(votacao.getSessaoId())).thenReturn(Optional.of(sessaoEntityMock));

        //when(votacaoRepository.(votacaoId)).thenReturn(votacao);


    }

    @Test
    public void deveRetornarVotacaoAoSalvar() {

        votacao votacao = new votacao(votacaoId, escolha, sessaoId, associadoId);

        com.abnergmf.votesapi.domain.votacao votacaoSalva = votacaoRepository.salvar(votacao);

        Assert.assertEquals(votacao, votacaoSalva);
    }

    @Test(expected =  VoteAPIObjectNotFoundException.class)
    public void deveCairNaVoteAPIObjectNotFoundExceptionAoTentarSalvarVotacao() {
        Long sessaoId = -1L;
        votacao votacao = new votacao(votacaoId, escolha, sessaoId, associadoId);
        votacaoRepository.salvar(votacao);
    }


}