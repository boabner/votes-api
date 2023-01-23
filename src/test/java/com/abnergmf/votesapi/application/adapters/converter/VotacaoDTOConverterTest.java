package com.abnergmf.votesapi.application.adapters.converter;

import static org.mockito.Mockito.when;

import java.util.Date;

import com.abnergmf.votesapi.application.adapters.controller.form.VotacaoForm;
import com.abnergmf.votesapi.domain.dtos.VotacaoDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class VotacaoDTOConverterTest {

    @Mock
    private VotacaoDTOConverter votacaoConverter;

    private Long sessaoId;
    private Long associadoId;
    private String escolha;
    @Before
    public void init() {
        sessaoId = 1L;
        associadoId = 1L;
        escolha = "S";
    }

    @Test
    public void deveRetornarVotacaoDTOAoChamarVotacaoFormToVotacaoDTO() {

        VotacaoForm votacaoForm = new VotacaoForm(sessaoId, escolha, associadoId);
        VotacaoDTO votacaoDTO = new VotacaoDTO(escolha, sessaoId, associadoId);

        when(votacaoConverter.votacaoFormToVotacaoDTO(votacaoForm)).thenReturn(votacaoDTO);

        Assert.assertEquals(votacaoDTO, votacaoConverter.votacaoFormToVotacaoDTO(votacaoForm));

    }


}
