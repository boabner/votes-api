package com.abnergmf.votesapi.application.adapters.converter;

import static org.mockito.Mockito.when;

import java.util.Date;

import com.abnergmf.votesapi.application.adapters.controller.form.SessaoAlterarForm;
import com.abnergmf.votesapi.application.adapters.controller.form.SessaoForm;
import com.abnergmf.votesapi.application.util.DateUtil;
import com.abnergmf.votesapi.domain.Sessao;
import com.abnergmf.votesapi.domain.dtos.SessaoAtivaDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SessaoAtivaDTOConverterTest {

    @Mock
    private SessaoAtivaDTOConverter sessaoConverter;

    private Long pautaId;
    private Long sessaoId;
    private String dataEncerramentoString;
    private Date dataEncerramento;
    @Before
    public void init() {
        pautaId = 1L;
        sessaoId = 1L;
        dataEncerramento = DateUtil.acrescentarMinutosNaData(new Date(), 30);
        dataEncerramentoString = DateUtil.converterDataEmString(dataEncerramento);
    }

    @Test
    public void deveRetornarSessaoAtivaDTOAoChamarSessaoToSessaoAtivaDTO() {

        Sessao sessao = new Sessao(sessaoId, new Date(), dataEncerramento, pautaId);
        SessaoAtivaDTO sessaoAtivaDTO = new SessaoAtivaDTO(sessaoId, DateUtil.converterStringEmData(dataEncerramentoString), pautaId);

        when(sessaoConverter.sessaoToSessaoAtivaDTO(sessao)).thenReturn(sessaoAtivaDTO);

        Assert.assertEquals(sessaoAtivaDTO, sessaoConverter.sessaoToSessaoAtivaDTO(sessao));

    }

}
