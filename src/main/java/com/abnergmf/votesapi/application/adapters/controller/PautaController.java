package com.abnergmf.votesapi.application.adapters.controller;

import java.util.List;

import com.abnergmf.votesapi.domain.dtos.PautaDTO;
import com.abnergmf.votesapi.domain.ports.interfaces.PautaServicePort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pautas")
public class PautaController {

    private final PautaServicePort pautaServicePort;

    public PautaController(PautaServicePort pautaServicePort) {
        this.pautaServicePort = pautaServicePort;
    }

    @PostMapping
    void criarPauta(@RequestBody PautaDTO pautaDTO) {
        pautaServicePort.criarPauta(pautaDTO);
    }

    @PutMapping
    void atualizarPauta(@PathVariable Long idPauta, @RequestBody PautaDTO pautaDTO) throws Exception{
        pautaServicePort.atualizarPauta(idPauta, pautaDTO);
    }

    @GetMapping
    List<PautaDTO> getPautas() {
        return pautaServicePort.listarPautas();
    }
}
