package com.abnergmf.votesapi.application.adapters.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import com.abnergmf.votesapi.application.adapters.controller.form.SessaoAlterarForm;
import com.abnergmf.votesapi.application.adapters.controller.form.SessaoForm;
import com.abnergmf.votesapi.application.adapters.converter.SessaoDTOConverter;
import com.abnergmf.votesapi.application.util.DateUtil;
import com.abnergmf.votesapi.domain.ports.interfaces.SessaoServicePort;
import com.abnergmf.votesapi.infrastructure.adapters.repositories.SessaoRepositoryDAO;
import com.abnergmf.votesapi.utils.JsonUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(SessaoController.class)
public class SessaoControlleTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    private SessaoServicePort sessaoServicePort;

    @MockBean
    private SessaoDTOConverter sessaoDTOConverter;

    @MockBean
    SessaoRepositoryDAO sessaoRepositoryDAO;

    private Long pautaId;
    private String dataEncerramento;
    @Before
    public void init() {
        pautaId = 1L;
        Date dataAConverter = DateUtil.acrescentarMinutosNaData(new Date(), 30);
        dataEncerramento = DateUtil.converterDataEmString(dataAConverter);
    }

    @Test
    public void deveRetornarCreatedAoChamarAbrirSessaoSemDataEncerramentoEComPautaId() throws Exception {

        SessaoForm sessao = new SessaoForm(pautaId, null);

        mockMvc.perform(MockMvcRequestBuilders.post("/sessao")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(JsonUtils.asJsonString(sessao))
        ).andExpect(status().isCreated());

    }

    @Test
    public void deveRetornarCreatedAoChamarAbrirSessaoComDataEncerramentoEComPautaId() throws Exception {

        SessaoForm sessao = new SessaoForm(pautaId, dataEncerramento);

        mockMvc.perform(MockMvcRequestBuilders.post("/sessao")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(JsonUtils.asJsonString(sessao))
        ).andExpect(status().isCreated());

    }

    @Test
    public void deveRetornarBadRequestAoChamarAbrirSessaoComDataEncerramentoESemPautaId() throws Exception {

        SessaoForm sessao = new SessaoForm(null, dataEncerramento);

        mockMvc.perform(MockMvcRequestBuilders.post("/sessao")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(JsonUtils.asJsonString(sessao))
        ).andExpect(status().isBadRequest());

    }

    @Test
    public void deveRetornarOkAoChamarAtualizarSessaoComDataEncerramento() throws Exception {

        SessaoAlterarForm sessao = new SessaoAlterarForm(dataEncerramento);

        mockMvc.perform(MockMvcRequestBuilders.put("/sessao/{id}", pautaId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(JsonUtils.asJsonString(sessao))
        ).andExpect(status().isOk());

    }

    @Test
    public void deveRetornarBadRequestAoChamarAtualizarSessaoComDataEncerramentoNull() throws Exception {

        SessaoAlterarForm sessao = new SessaoAlterarForm(null);

        mockMvc.perform(MockMvcRequestBuilders.put("/sessao/{id}", pautaId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(JsonUtils.asJsonString(sessao))
        ).andExpect(status().isBadRequest());

    }

    @Test
    public void deveRetornarOkAoChamarRemoverSessaoComIdExistente() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/sessao/{id}", pautaId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

    }

    @Test
    public void deveRetornarBadRequestAoChamarRemoverSessaoComIdInexistente() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/sessao/{id}", -1L)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());

    }

    @Test
    public void deveRetornarOkAoChamarListarSessaos() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/sessao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    public void deveRetornarOkAoChamarListarSessoesAtivas() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/sessao/listar-ativas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

}
