package com.abnergmf.votesapi.infrastructure.adapters.repositories;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.abnergmf.votesapi.application.error.VoteAPIObjectNotFoundException;
import com.abnergmf.votesapi.domain.Pauta;
import com.abnergmf.votesapi.infrastructure.adapters.converter.PautaConverter;
import com.abnergmf.votesapi.infrastructure.adapters.entities.PautaEntity;
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
public class PautaRepositoryTest{

    @InjectMocks
    private PautaRepository pautaRepository;
    @Mock
    private PautaRepositoryDAO pautaRepositoryDAO;

    @Mock
    private PautaConverter pautaConverter;

    @Before
    public void init() {
        Long idPautaExistente = 1L;

        Pauta pauta = new Pauta(idPautaExistente, "Pauta para teste");
        PautaEntity pautaEntityMock = mock(PautaEntity.class);

        when(pautaRepositoryDAO.findById(idPautaExistente)).thenReturn(Optional.of(pautaEntityMock));

        PautaEntity pautaEntity = pautaConverter.toPautaEntity(pauta);
        when(pautaConverter.toPauta(pautaEntity)).thenReturn(pauta);

        when(pautaRepository.getById(idPautaExistente)).thenReturn(pauta);

    }
    @Test
    public void deveCarregarPautaAoBuscarPeloId() {

        Long idPauta = 1L;
        Pauta pauta = pautaRepository.getById(idPauta);

        Assert.assertNotNull(pauta);
        Assert.assertEquals(idPauta, pauta.getId());
    }

    @Test(expected = VoteAPIObjectNotFoundException.class)
    public void naoDeveCarregarPautaAoBuscarPeloId() {
        Long idPauta = 5L;
        pautaRepository.getById(idPauta);
    }

    @Test
    public void deveRetornarTrueAoRemoverPauta() {

        Long idPauta = 1L;
        Pauta pauta = pautaRepository.getById(idPauta);

        Boolean isRemoved = pautaRepository.remover(pauta);

        Assert.assertEquals(isRemoved, true);

    }

    @Test(expected = VoteAPIObjectNotFoundException.class)
    public void deveCairNaVoteAPIObjectNotFoundExceptionAoTentarRemoverPauta() {

        Long idPauta = 5L;
        Pauta pauta = pautaRepository.getById(idPauta);
        pautaRepository.remover(pauta);
    }

    @Test
    public void deveRetornarPautaAoSalvar() {

        Long idPauta = 1L;
        Pauta pauta = pautaRepository.getById(idPauta);

        Pauta pautaSalva = pautaRepository.persistir(pauta);

        Assert.assertEquals(pauta, pautaSalva);
    }

    @Test(expected = VoteAPIObjectNotFoundException.class)
    public void deveCairNaVoteAPIObjectNotFoundExceptionAoTentarSalvarPauta() {

        Long idPauta = 5L;
        Pauta pauta = pautaRepository.getById(idPauta);
        pautaRepository.persistir(pauta);

    }

}