package com.abnergmf.votesapi.domain;

import static org.mockito.Mockito.when;

import com.abnergmf.votesapi.application.error.VoteAPIObjectNotFoundException;
import com.abnergmf.votesapi.domain.adapters.services.PautaServiceImpl;
import com.abnergmf.votesapi.domain.dtos.PautaDTO;
import com.abnergmf.votesapi.infrastructure.adapters.repositories.PautaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PautaServiceImplTest {

    @Mock
    private PautaServiceImpl pautaServiceImpl;

    @Mock
    private PautaRepository pautaRepository;

    @Before
    public void init() {

        Pauta pauta = new Pauta(1L, "Pauta para teste");
        PautaDTO pautaDTO = new PautaDTO(1L, "PautaDTO para teste");

        when(pautaRepository.persistir(pauta)).thenReturn(pauta);
    }

    @Test
    public void deveRetornarPautaAoChamarCriarPauta() {

        Long idPauta = 1L;
        Pauta pauta = new Pauta(idPauta, "Pauta para teste");
        PautaDTO pautaDTO = new PautaDTO(idPauta, "Pauta para teste");

        when(pautaServiceImpl.criarPauta(pautaDTO)).thenReturn(pauta);
        Pauta pautaSalva = pautaServiceImpl.criarPauta(pautaDTO);

        Assert.notNull(pautaSalva);
    }

    @Test(expected = VoteAPIObjectNotFoundException.class)
    public void deveRetornarVoteAPIObjectNotFoundExceptionAoTentarCriarPauta() {

        Long idPauta = 55L;
        PautaDTO pautaDTO = new PautaDTO(idPauta, "Pauta para teste");

        when(pautaServiceImpl.criarPauta(pautaDTO)).thenThrow(new VoteAPIObjectNotFoundException("Pauta", idPauta));

        pautaServiceImpl.criarPauta(pautaDTO);

    }

    @Test
    public void deveRetornarPautaAoChamarAtualizarPauta() {

        Long idPauta = 1L;
        Pauta pauta = new Pauta(idPauta, "Pauta para teste");
        PautaDTO pautaDTO = new PautaDTO(1L, "Pauta para teste");

        when(pautaServiceImpl.atualizarPauta(idPauta, pautaDTO)).thenReturn(pauta);
        Pauta pautaSalva = pautaServiceImpl.atualizarPauta(idPauta, pautaDTO);

        Assert.notNull(pautaSalva);
    }

    @Test(expected = VoteAPIObjectNotFoundException.class)
    public void deveRetornarVoteAPIObjectNotFoundExceptionAoTentarAtualizarPauta() {

        Long idPauta = 45L;
        PautaDTO pautaDTO = new PautaDTO(idPauta, "Pauta para teste");

        when(pautaServiceImpl.atualizarPauta(idPauta, pautaDTO)).thenThrow(new VoteAPIObjectNotFoundException("Pauta", idPauta));

        pautaServiceImpl.atualizarPauta(idPauta, pautaDTO);

    }

    @Test
    public void deveConcluirAoChamarRemoverPauta() {

        Long idPauta = 1L;
        pautaServiceImpl.removerPauta(idPauta);

    }

    @Test(expected = VoteAPIObjectNotFoundException.class)
    public void deveRetornarVoteAPIObjectNotFoundExceptionAoTentarRemoverPauta() {

        Long idPauta = 45L;

        when(pautaServiceImpl.removerPauta(idPauta)).thenThrow(new VoteAPIObjectNotFoundException("Pauta", idPauta));

        pautaServiceImpl.removerPauta(idPauta);

    }

}
