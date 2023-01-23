package com.abnergmf.votesapi.application.adapters.controller;

import java.util.List;
import javax.transaction.Transactional;
import javax.validation.Valid;

import com.abnergmf.votesapi.application.adapters.controller.form.SessaoForm;
import com.abnergmf.votesapi.application.adapters.converter.SessaoDTOConverter;
import com.abnergmf.votesapi.domain.dtos.SessaoResultadoDTO;
import com.abnergmf.votesapi.domain.dtos.SessaoDTO;
import com.abnergmf.votesapi.domain.ports.interfaces.SessaoServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sessao")
public class SessaoController {

    @Autowired
    private SessaoServicePort sessaoServicePort;
    @Autowired
    private SessaoDTOConverter sessaoFormConverter;

    @PostMapping
    @Transactional
    public ResponseEntity<SessaoDTO> abrirSessao(
            @RequestBody @Valid SessaoForm sessaoForm
    ) {
        sessaoServicePort.processarAberturaDeSessao(
                sessaoServicePort.prepararAberturaSessao(sessaoFormConverter.sessaoToSessaoDTO(sessaoForm))
        );
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    List<SessaoResultadoDTO> listarSessaos() {
        return sessaoServicePort.listarSessaos();
    }

    @GetMapping("/listar-ativas")
    List<SessaoResultadoDTO> listarSessoesAtivas() {
        return sessaoServicePort.listarSessoesAtivas();
    }


}