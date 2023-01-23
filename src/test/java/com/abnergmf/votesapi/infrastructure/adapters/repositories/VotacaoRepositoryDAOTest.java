package com.abnergmf.votesapi.infrastructure.adapters.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import com.abnergmf.votesapi.application.util.DateUtil;
import com.abnergmf.votesapi.infrastructure.adapters.entities.PautaEntity;
import com.abnergmf.votesapi.infrastructure.adapters.entities.SessaoEntity;
import com.abnergmf.votesapi.infrastructure.adapters.entities.VotacaoEntity;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VotacaoRepositoryDAOTest {

    @Autowired
    private VotacaoRepositoryDAO votacaoRepositoryDAO;
    @Autowired
    private PautaRepositoryDAO pautaRepositoryDAO;
    @Autowired
    private SessaoRepositoryDAO sessaoRepositoryDAO;
    private VotacaoEntity votacaoEntity;
    private PautaEntity pautaEntity;
    private SessaoEntity sessaoEntity;

    @Before
    public void init() {
        prepararAmbiente();
    }

    @After
    public void destroy() {
        finalizarAmbiente();
    }

    @Test
    public void deveRetornarListVotacaoEntityAoChamarFindAllBySessaoEntityId() {

        List<VotacaoEntity> votacaoEntityList = votacaoRepositoryDAO.findAllBySessaoEntityId(sessaoEntity.getId());

        Assert.assertFalse(votacaoEntityList.isEmpty());
    }

    @Test
    public void naoDeveRetornarOptionalVotacaoEntityAoChamarFindById() {
        Optional<VotacaoEntity> optionalVotacaoEntity = votacaoRepositoryDAO.findById(-1L);
        Assert.assertFalse(optionalVotacaoEntity.isPresent());
    }

    @Transactional
    private void prepararAmbiente() {
        pautaEntity = gerarPautaEntityParaTeste();
        sessaoEntity = gerarSessaoEntityParaTeste();
        votacaoEntity = gerarVotacaoEntityParaTeste();
    }

    @Transactional
    private void finalizarAmbiente() {
        votacaoRepositoryDAO.delete(votacaoEntity);
        sessaoRepositoryDAO.delete(sessaoEntity);
        pautaRepositoryDAO.delete(pautaEntity);
    }

    private PautaEntity gerarPautaEntityParaTeste() {
        return pautaRepositoryDAO.save(new PautaEntity("Pauta para teste"));
    }

    private SessaoEntity gerarSessaoEntityParaTeste() {
        Date dataEncerramento = DateUtil.acrescentarMinutosNaData(new Date(), 30);
        return sessaoRepositoryDAO.save(new SessaoEntity(new Date(), dataEncerramento, pautaEntity));
    }

    private VotacaoEntity gerarVotacaoEntityParaTeste() {
        Long associadoId = 1L;
        VotacaoEntity votacaoEntity = new VotacaoEntity(null, "S", sessaoEntity, associadoId);
        votacaoRepositoryDAO.save(votacaoEntity);
        return votacaoEntity;
    }
}
