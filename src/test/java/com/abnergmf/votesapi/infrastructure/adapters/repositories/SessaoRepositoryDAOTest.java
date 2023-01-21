package com.abnergmf.votesapi.infrastructure.adapters.repositories;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import com.abnergmf.votesapi.infrastructure.adapters.entities.PautaEntity;
import com.abnergmf.votesapi.infrastructure.adapters.entities.SessaoEntity;
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
public class SessaoRepositoryDAOTest {

    @Autowired
    private SessaoRepositoryDAO sessaoRepositoryDAO;

    @Autowired
    private PautaRepositoryDAO pautaRepositoryDAO;

    private SessaoEntity sessaoEntity;
    private PautaEntity pautaEntity;

    @Before
    public void init() {
        prepararAmbiente();
    }

    @After
    public void destroy() {
        finalizarAmbiente();
    }

    @Test
    public void deveRetornarOptionalSessaoEntityAoChamarFindById() {

        Optional<SessaoEntity> optionalSessaoEntity = sessaoRepositoryDAO.findById(sessaoEntity.getId());

        Assert.assertTrue(optionalSessaoEntity.isPresent());

        sessaoRepositoryDAO.delete(sessaoEntity);
    }

    @Test
    public void naoDeveRetornarOptionalSessaoEntityAoChamarFindById() {
        Optional<SessaoEntity> optionalSessaoEntity = sessaoRepositoryDAO.findById(-1L);
        Assert.assertFalse(optionalSessaoEntity.isPresent());
    }

    @Transactional
    private void prepararAmbiente() {
        sessaoEntity = gerarSessaoEntityParaTeste();
        pautaEntity = gerarPautaEntityParaTeste();
    }

    @Transactional
    private void finalizarAmbiente() {
        sessaoRepositoryDAO.delete(sessaoEntity);
        pautaRepositoryDAO.delete(pautaEntity);
    }

    private PautaEntity gerarPautaEntityParaTeste() {
        return pautaRepositoryDAO.save(new PautaEntity("Pauta para teste"));
    }

    private SessaoEntity gerarSessaoEntityParaTeste() {
        SessaoEntity sessaoEntity = new SessaoEntity(null, new Date(), new Date(), pautaEntity);
        sessaoRepositoryDAO.save(sessaoEntity);
        return sessaoEntity;
    }
}
