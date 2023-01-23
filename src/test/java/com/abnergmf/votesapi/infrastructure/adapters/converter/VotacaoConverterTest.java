package com.abnergmf.votesapi.infrastructure.adapters.converter;

import static org.mockito.Mockito.when;

import java.util.Date;

import com.abnergmf.votesapi.application.util.DateUtil;
import com.abnergmf.votesapi.domain.votacao;
import com.abnergmf.votesapi.infrastructure.adapters.entities.PautaEntity;
import com.abnergmf.votesapi.infrastructure.adapters.entities.SessaoEntity;
import com.abnergmf.votesapi.infrastructure.adapters.entities.VotacaoEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class VotacaoConverterTest {

    @Mock
    private VotacaoConverter votacaoConverter;

    private com.abnergmf.votesapi.domain.votacao votacao;
    private VotacaoEntity votacaoEntity;

    @Test
    public void deveRetornarVotacaoAoChamarToVotacao() {

        Long pautaId = 1L;
        Long sessaoId = 1L;
        Long votacaoId = 1L;
        Long associadoId = 1L;

        String escolha = "S";

        Date dataEncerramento = DateUtil.acrescentarMinutosNaData(new Date(), 30);

        votacao = new votacao(votacaoId, escolha, sessaoId, associadoId);

        PautaEntity pautaEntity = new PautaEntity(pautaId, "Pauta para teste");

        SessaoEntity sessaoEntity = new SessaoEntity(sessaoId, new Date(), dataEncerramento, pautaEntity);

        votacaoEntity = new VotacaoEntity(votacaoId, escolha, sessaoEntity, associadoId);

        when(votacaoConverter.toVotacao(votacaoEntity)).thenReturn(votacao);

        Assert.assertEquals(votacao, votacaoConverter.toVotacao(votacaoEntity));

    }

    @Test
    public void deveRetornarVotacaoEntityAoChamarToVotacaoEntity() {

        when(votacaoConverter.toVotacao(votacaoEntity)).thenReturn(votacao);

        Assert.assertEquals(votacao, votacaoConverter.toVotacao(votacaoEntity));

    }

}