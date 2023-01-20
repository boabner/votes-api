package com.abnergmf.votesapi.application.adapters.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.abnergmf.votesapi.application.adapters.controller.form.PautaForm;
import com.abnergmf.votesapi.application.adapters.converter.PautaDTOConverter;
import com.abnergmf.votesapi.domain.ports.interfaces.PautaServicePort;
import com.abnergmf.votesapi.infrastructure.adapters.repositories.PautaRepositoryDAO;
import com.abnergmf.votesapi.utils.JsonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(PautaController.class)
public class PautaControlleTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    private PautaServicePort pautaServicePort;

    @MockBean
    private PautaDTOConverter pautaDTOConverter;

    @MockBean
    PautaRepositoryDAO pautaRepositoryDAO;

    private static final Logger logger = LoggerFactory.getLogger(PautaController.class.getName());

    @Test
    public void deveRetornarCreatedAoChamarCriarPauta() throws Exception {

        PautaForm pauta = new PautaForm("Pauta para teste");

        mockMvc.perform(MockMvcRequestBuilders.post("/pautas")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(JsonUtils.asJsonString(pauta))
        ).andExpect(status().isCreated());

    }

    @Test
    public void deveRetornarBadRequestAoChamarCriarPautaComNomeNull() throws Exception {

        PautaForm pauta = new PautaForm(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/pautas")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(JsonUtils.asJsonString(pauta))
        ).andExpect(status().isBadRequest());

    }

    @Test
    public void deveRetornarBadRequestAoChamarCriarPautaComNomeVazio() throws Exception {

        PautaForm pauta = new PautaForm("");

        mockMvc.perform(MockMvcRequestBuilders.post("/pautas")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(JsonUtils.asJsonString(pauta))
        ).andExpect(status().isBadRequest());

    }

    @Test
    public void deveRetornarOkAoChamarAtualizarPauta() throws Exception {

        PautaForm pauta = new PautaForm("Pauta para teste");

        mockMvc.perform(MockMvcRequestBuilders.put("/pautas/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(JsonUtils.asJsonString(pauta))
        ).andExpect(status().isOk());

    }

    @Test
    public void deveRetornarBadRequestAoChamarAtualizarPautaComNomeNull() throws Exception {

        PautaForm pauta = new PautaForm(null);

        mockMvc.perform(MockMvcRequestBuilders.put("/pautas/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(JsonUtils.asJsonString(pauta))
        ).andExpect(status().isBadRequest());

    }

    @Test
    public void deveRetornarBadRequestAoChamarAtualizarPautaComNomeVazio() throws Exception {

        PautaForm pauta = new PautaForm("");

        mockMvc.perform(MockMvcRequestBuilders.put("/pautas/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(JsonUtils.asJsonString(pauta))
        ).andExpect(status().isBadRequest());

    }

    @Test
    public void deveRetornarOkAoChamarRemoverPauta() throws Exception {

        PautaForm pauta = new PautaForm("Pauta para teste");

        mockMvc.perform(MockMvcRequestBuilders.delete("/pautas/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

    }

    @Test
    public void deveRetornarBadRequestAoChamarRemoverPautaComIdInexistente() throws Exception {

        PautaForm pauta = new PautaForm(null);

        mockMvc.perform(MockMvcRequestBuilders.put("/pautas/{id}", -1L)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());

    }

    @Test
    public void deveRetornarOkAoChamarListarPautas() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/pautas")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk());
    }

}
