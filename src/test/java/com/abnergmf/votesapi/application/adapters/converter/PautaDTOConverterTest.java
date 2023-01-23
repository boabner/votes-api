package com.abnergmf.votesapi.application.adapters.converter;

import static org.mockito.Mockito.when;

import com.abnergmf.votesapi.application.adapters.controller.form.PautaForm;
import com.abnergmf.votesapi.domain.Pauta;
import com.abnergmf.votesapi.domain.dtos.PautaDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PautaDTOConverterTest {

    @Mock
    private PautaDTOConverter pautaConverter;

    @Test
    public void deveRetornarPautaDTOAoChamarToPautaDTOComPautaForm() {

        PautaForm pautaForm = new PautaForm("Pauta teste");
        PautaDTO pautaDTO = new PautaDTO("Pauta teste");

        when(pautaConverter.toPautaDTO(pautaForm)).thenReturn(pautaDTO);

        Assert.assertEquals(pautaDTO, pautaConverter.toPautaDTO(pautaForm));

    }

    @Test
    public void deveRetornarPautaDTOAoChamarToPautaComPauta() {

        PautaDTO pautaDTO = new PautaDTO("Pauta teste");
        Pauta pauta = new Pauta(1L, "PautaEntity teste");

        when(pautaConverter.toPautaDTO(pauta)).thenReturn(pautaDTO);

        Assert.assertEquals(pautaDTO, pautaConverter.toPautaDTO(pauta));

    }
}
