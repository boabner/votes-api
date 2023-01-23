package com.abnergmf.votesapi.application.adapters.converter;

import static org.mockito.Mockito.when;

import java.util.Date;

import com.abnergmf.votesapi.application.adapters.controller.form.SessaoAlterarForm;
import com.abnergmf.votesapi.application.adapters.controller.form.SessaoForm;
import com.abnergmf.votesapi.application.util.DateUtil;
import com.abnergmf.votesapi.domain.Sessao;
import com.abnergmf.votesapi.domain.dtos.SessaoDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SessaoDTOConverterTest {

    @Mock
    private SessaoDTOConverter sessaoConverter;

    private Long pautaId;
    private String dataEncerramentoString;
    private Date dataEncerramento;
    @Before
    public void init() {
        pautaId = 1L;
        dataEncerramento = DateUtil.acrescentarMinutosNaData(new Date(), 30);
        dataEncerramentoString = DateUtil.converterDataEmString(dataEncerramento);
    }

    @Test
    public void deveRetornarSessaoDTOAoChamarSessaoFormToSessaoDTO() {

        SessaoForm sessaoForm = new SessaoForm(pautaId, dataEncerramentoString);
        SessaoDTO sessaoDTO = new SessaoDTO(pautaId, DateUtil.converterStringEmData(dataEncerramentoString));

        when(sessaoConverter.sessaoToSessaoDTO(sessaoForm)).thenReturn(sessaoDTO);

        Assert.assertEquals(sessaoDTO, sessaoConverter.sessaoToSessaoDTO(sessaoForm));

    }

    @Test
    public void deveRetornarSessaoDTOAoChamarSessaoToSessaoDTO() {

        SessaoDTO sessaoDTO = new SessaoDTO(pautaId, dataEncerramento);
        Sessao sessao = new Sessao(sessaoDTO);

        when(sessaoConverter.sessaoToSessaoDTO(sessao)).thenReturn(sessaoDTO);

        Assert.assertEquals(sessaoDTO, sessaoConverter.sessaoToSessaoDTO(sessao));

    }

    @Test
    public void deveRetornarSessaoDTOAoChamarAlterarFormToSessaoDTO() {

        SessaoAlterarForm sessaoAlterarForm = new SessaoAlterarForm(dataEncerramentoString);
        SessaoDTO sessaoDTO = new SessaoDTO(pautaId, dataEncerramento);

        when(sessaoConverter.alterarFormtoSessaoDTO(sessaoAlterarForm)).thenReturn(sessaoDTO);

        Assert.assertEquals(sessaoDTO, sessaoConverter.alterarFormtoSessaoDTO(sessaoAlterarForm));

    }


}
