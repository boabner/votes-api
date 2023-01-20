package com.abnergmf.votesapi.application.adapters.controller;

import java.util.List;
import javax.transaction.Transactional;
import javax.validation.Valid;

import com.abnergmf.votesapi.application.adapters.controller.form.SessaoAlterarForm;
import com.abnergmf.votesapi.application.adapters.controller.form.SessaoForm;
import com.abnergmf.votesapi.application.adapters.converter.SessaoDTOConverter;
import com.abnergmf.votesapi.domain.Sessao;
import com.abnergmf.votesapi.domain.dtos.SessaoAtivaDTO;
import com.abnergmf.votesapi.domain.dtos.SessaoDTO;
import com.abnergmf.votesapi.domain.ports.interfaces.SessaoServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
        sessaoServicePort.abrirSessao(
                sessaoServicePort.prepararAberturaSessao(sessaoFormConverter.sessaoFormToSessaoDTO(sessaoForm))
        );
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualizarSessao(@PathVariable Long id, @RequestBody @Valid SessaoAlterarForm sessaoForm) {

        Sessao sessao = sessaoServicePort.atualizarSessao(id, sessaoFormConverter.alterarFormtoSessaoDTO(sessaoForm));

        return ResponseEntity.ok(sessao);
    }

    @GetMapping
    List<SessaoDTO> listarSessaos() {
        return sessaoServicePort.listarSessaos();
    }

    @GetMapping("/listar-ativas")
    List<SessaoAtivaDTO> listarSessoesAtivas() {
        return sessaoServicePort.listarSessoesAtivas();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> removerSessao(@PathVariable Long id) throws Exception {

        sessaoServicePort.removerSessao(id);

        return ResponseEntity.ok().build();
    }

}