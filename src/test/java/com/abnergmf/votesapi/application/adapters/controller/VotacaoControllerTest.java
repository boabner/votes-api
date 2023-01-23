package com.abnergmf.votesapi.application.adapters.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.abnergmf.votesapi.application.adapters.controller.form.VotacaoForm;
import com.abnergmf.votesapi.application.adapters.converter.VotacaoDTOConverter;
import com.abnergmf.votesapi.domain.ports.interfaces.SessaoServicePort;
import com.abnergmf.votesapi.domain.ports.interfaces.VotacaoServicePort;
import com.abnergmf.votesapi.infrastructure.adapters.repositories.VotacaoRepositoryDAO;
import com.abnergmf.votesapi.utils.JsonUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(VotacaoController.class)
public class VotacaoControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    private VotacaoServicePort votacaoServicePort;
    @MockBean
    private SessaoServicePort sessaoServicePort;
    @MockBean
    private VotacaoDTOConverter votacaoDTOConverter;

    @MockBean
    VotacaoRepositoryDAO votacaoRepositoryDAO;

    private Long sessaoId;
    @Before
    public void init() {
        sessaoId = 1L;
    }

    @Test
    public void deveRetornarCreatedAoChamarRegistrarVotoComEscolhaEComSessaoId() throws Exception {

        VotacaoForm votacao = new VotacaoForm(sessaoId, "S");

        mockMvc.perform(MockMvcRequestBuilders.post("/votacao/registrar-voto")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(JsonUtils.asJsonString(votacao))
        ).andExpect(status().isCreated());

    }

    @Test
    public void deveRetornarBadRequestAoChamarRegistrarVotoComEscolhaESemSessaoId() throws Exception {

        VotacaoForm votacao = new VotacaoForm(null, "S");

        mockMvc.perform(MockMvcRequestBuilders.post("/votacao/registrar-voto")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(JsonUtils.asJsonString(votacao))
        ).andExpect(status().isBadRequest());

    }

    @Test
    public void deveRetornarBadRequestAoChamarRegistrarVotoComEscolhaInvalidaEComSessaoId() throws Exception {

        VotacaoForm votacao = new VotacaoForm(sessaoId, "D");

        mockMvc.perform(MockMvcRequestBuilders.post("/votacao/registrar-voto")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(JsonUtils.asJsonString(votacao))
        ).andExpect(status().isBadRequest());

        votacao = new VotacaoForm(sessaoId, "s");

        mockMvc.perform(MockMvcRequestBuilders.post("/votacao/registrar-voto")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(JsonUtils.asJsonString(votacao))
        ).andExpect(status().isBadRequest());

    }


    @Test
    public void deveRetornarOkAoChamarResultadoVotacao() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/votacao/resultado/sessao="+sessaoId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

}
