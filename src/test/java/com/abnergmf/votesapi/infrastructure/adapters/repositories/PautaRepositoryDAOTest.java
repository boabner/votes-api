package com.abnergmf.votesapi.infrastructure.adapters.repositories;

import java.util.Optional;

import javax.transaction.Transactional;

import com.abnergmf.votesapi.infrastructure.adapters.entities.PautaEntity;
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
public class PautaRepositoryDAOTest {

    @Autowired
    private PautaRepositoryDAO pautaRepositoryDAO;

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
    public void deveRetornarOptionalPautaEntityAoChamarFindById() {
        Optional<PautaEntity> optionalPautaEntity = pautaRepositoryDAO.findById(1L);
        Assert.assertTrue(optionalPautaEntity.isPresent());
    }

    @Test
    public void naoDeveRetornarOptionalPautaEntityAoChamarFindById() {
        Optional<PautaEntity> optionalPautaEntity = pautaRepositoryDAO.findById(-1L);
        Assert.assertFalse(optionalPautaEntity.isPresent());
    }

    @Transactional
    private void prepararAmbiente() {
        pautaEntity = gerarPautaEntityParaTeste();
    }

    @Transactional
    private void finalizarAmbiente() {
        pautaRepositoryDAO.delete(pautaEntity);
    }

    private PautaEntity gerarPautaEntityParaTeste() {
        return pautaRepositoryDAO.save(new PautaEntity("Pauta para teste"));
    }

}
