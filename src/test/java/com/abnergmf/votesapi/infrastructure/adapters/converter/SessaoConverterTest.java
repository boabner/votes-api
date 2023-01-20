package com.abnergmf.votesapi.infrastructure.adapters.converter;

import static org.mockito.Mockito.when;

import java.util.Date;

import com.abnergmf.votesapi.application.util.DateUtil;
import com.abnergmf.votesapi.domain.Sessao;
import com.abnergmf.votesapi.infrastructure.adapters.entities.PautaEntity;
import com.abnergmf.votesapi.infrastructure.adapters.entities.SessaoEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SessaoConverterTest {

    @Mock
    private SessaoConverter sessaoConverter;

    private Sessao sessao;
    private SessaoEntity sessaoEntity;

    @Test
    public void deveRetornarSessaoAoChamarToSessao() {

        Long pautaId = 1L;
        Long sessaoId = 1L;
        Date dataEncerramento = DateUtil.acrescentarMinutosNaData(new Date(), 30);

        sessao = new Sessao(sessaoId, new Date(), dataEncerramento, pautaId);

        PautaEntity pautaEntity = new PautaEntity(pautaId, "Pauta para teste");

        sessaoEntity = new SessaoEntity(sessaoId, new Date(), dataEncerramento, pautaEntity);

        when(sessaoConverter.toSessao(sessaoEntity)).thenReturn(sessao);

        Assert.assertEquals(sessao, sessaoConverter.toSessao(sessaoEntity));

    }

    @Test
    public void deveRetornarSessaoEntityAoChamarToSessaoEntity() {

        when(sessaoConverter.toSessao(sessaoEntity)).thenReturn(sessao);

        Assert.assertEquals(sessao, sessaoConverter.toSessao(sessaoEntity));

    }

}