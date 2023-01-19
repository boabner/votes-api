package com.abnergmf.votesapi.application.adapters.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.abnergmf.votesapi.application.adapters.controller.form.PautaForm;
import com.abnergmf.votesapi.domain.Pauta;
import com.abnergmf.votesapi.domain.dtos.PautaDTO;
import com.abnergmf.votesapi.domain.ports.interfaces.PautaServicePort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("pautas")
public class PautaController {

    private final PautaServicePort pautaServicePort;

    public PautaController(PautaServicePort pautaServicePort) {
        this.pautaServicePort = pautaServicePort;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> criarPauta(
        @RequestBody @Valid PautaForm pautaForm,
        UriComponentsBuilder uriBuilder
    ) {
        Pauta pauta = pautaServicePort.criarPauta(pautaForm.toPautaDTO());
        URI uri = uriBuilder.path("/").buildAndExpand(pauta).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualizarPauta(@PathVariable Long id, @RequestBody @Valid PautaForm pautaForm) throws Exception{
        Pauta pauta = pautaServicePort.atualizarPauta(id, pautaForm.toPautaDTO());
        return ResponseEntity.ok(pauta);
    }

    @GetMapping
    List<PautaDTO> getPautas() {
        return pautaServicePort.listarPautas();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) throws Exception {
        try {
            pautaServicePort.removerPauta(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new Exception();
        }
    }

}
