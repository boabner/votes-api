package com.abnergmf.votesapi.infrastructure.adapters.repositories;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import com.abnergmf.votesapi.application.error.VoteAPIObjectNotFoundException;
import com.abnergmf.votesapi.application.util.DateUtil;
import com.abnergmf.votesapi.domain.Sessao;
import com.abnergmf.votesapi.infrastructure.adapters.converter.SessaoConverter;
import com.abnergmf.votesapi.infrastructure.adapters.entities.PautaEntity;
import com.abnergmf.votesapi.infrastructure.adapters.entities.SessaoEntity;
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
public class SessaoRepositoryTest {

    @InjectMocks
    private SessaoRepository sessaoRepository;
    @Mock
    private SessaoConverter sessaoConverter;

    @Mock
    private SessaoRepositoryDAO sessaoRepositoryDAO;

    @Mock
    private PautaRepositoryDAO pautaRepositoryDAO;

    @Before
    public void init() {

        Long sessaoId = 1L;
        Long pautaId = 1L;

        Date dataEncerramento = DateUtil.acrescentarMinutosNaData(new Date(), 30);
        PautaEntity pautaEntity = new PautaEntity(1L, "Pauta para teste");

        Sessao sessaoMock = new Sessao(sessaoId, new Date(), dataEncerramento, pautaId);

        SessaoEntity sessaoEntityMock = mock(SessaoEntity.class);
        SessaoEntity sessaoEntity = new SessaoEntity(sessaoId, new Date(), dataEncerramento, pautaEntity);

        PautaEntity pautaEntityMock = mock(PautaEntity.class);

        when(sessaoRepositoryDAO.findById(sessaoId)).thenReturn(Optional.of(sessaoEntityMock));

        when(sessaoConverter.toSessao(sessaoEntity)).thenReturn(sessaoMock);

        Sessao sessao = sessaoConverter.toSessao(sessaoEntity);

        when(pautaRepositoryDAO.findById(sessao.getPautaId())).thenReturn(Optional.of(pautaEntityMock));

        when(sessaoRepository.getById(sessaoId)).thenReturn(sessao);


    }
    @Test
    public void deveCarregarSessaoAoBuscarPeloId() {

        Long sessaoId = 1L;
        Sessao sessao = sessaoRepository.getById(sessaoId);
        Assert.assertNotNull(sessao);

    }

    @Test(expected = VoteAPIObjectNotFoundException.class)
    public void naoDeveCarregarSessaoAoBuscarPeloId() {
        Long idSessao = 5L;
        sessaoRepository.getById(idSessao);
    }

    @Test
    public void deveRetornarSessaoAoSalvar() {

        Long sessaoId = 1L;

        Sessao sessao = sessaoRepository.getById(sessaoId);

        Sessao sessaoSalva = sessaoRepository.salvar(sessao);

        Assert.assertEquals(sessao, sessaoSalva);
    }

    @Test(expected = VoteAPIObjectNotFoundException.class)
    public void deveCairNaVoteAPIObjectNotFoundExceptionAoTentarSalvarSessao() {

        Long idSessao = -1L;
        Sessao sessao = sessaoRepository.getById(idSessao);
        sessaoRepository.salvar(sessao);

    }


}