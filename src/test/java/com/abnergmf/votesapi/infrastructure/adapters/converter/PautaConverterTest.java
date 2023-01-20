package com.abnergmf.votesapi.infrastructure.adapters.converter;

import static org.mockito.Mockito.when;

import com.abnergmf.votesapi.domain.Pauta;
import com.abnergmf.votesapi.infrastructure.adapters.entities.PautaEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PautaConverterTest {

    @Mock
    private PautaConverter pautaConverter;

    @Test
    public void deveRetornarPautaAoChamarToPauta() {

        Pauta pauta = new Pauta(1L, "Pauta teste");
        PautaEntity pautaEntity = new PautaEntity(1L, "PautaEntity teste");

        when(pautaConverter.toPauta(pautaEntity)).thenReturn(pauta);

        Assert.assertEquals(pauta, pautaConverter.toPauta(pautaEntity));

    }

    @Test
    public void deveRetornarPautaEntityAoChamarToPautaEntity() {

        Pauta pauta = new Pauta(1L, "Pauta teste");
        PautaEntity pautaEntity = new PautaEntity(1L, "PautaEntity teste");

        when(pautaConverter.toPautaEntity(pauta)).thenReturn(pautaEntity);

        Assert.assertEquals(pautaEntity, pautaConverter.toPautaEntity(pauta));

    }
}