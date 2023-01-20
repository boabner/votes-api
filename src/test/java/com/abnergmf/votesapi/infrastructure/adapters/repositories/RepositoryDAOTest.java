package com.abnergmf.votesapi.infrastructure.adapters.repositories;

import java.util.Optional;

import com.abnergmf.votesapi.infrastructure.adapters.entities.PautaEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepositoryDAOTest {

    @Autowired
    private PautaRepositoryDAO pautaRepositoryDAO;

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
}
