package com.abnergmf.votesapi.application.adapters.controller;

import java.util.List;

import com.abnergmf.votesapi.application.adapters.controller.form.PautaForm;
import com.abnergmf.votesapi.application.adapters.converter.PautaDTOConverter;
import com.abnergmf.votesapi.domain.Pauta;
import com.abnergmf.votesapi.domain.dtos.PautaDTO;
import com.abnergmf.votesapi.domain.ports.interfaces.PautaServicePort;
import javax.transaction.Transactional;
import javax.validation.Valid;

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
@RequestMapping("pautas")
public class PautaController {

    @Autowired
    private PautaServicePort pautaServicePort;
    @Autowired
    private PautaDTOConverter pautaFormConverter;

    @PostMapping
    @Transactional
    public ResponseEntity<PautaDTO> criarPauta(
        @RequestBody @Valid PautaForm pautaForm
    ) {
        pautaServicePort.criarPauta(pautaFormConverter.toPautaDTO(pautaForm));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualizarPauta(@PathVariable Long id, @RequestBody @Valid PautaForm pautaForm) {

        Pauta pauta = pautaServicePort.atualizarPauta(id, pautaFormConverter.toPautaDTO(pautaForm));

        return ResponseEntity.ok(pauta);
    }

    @GetMapping
    List<PautaDTO> listarPautas() {
        return pautaServicePort.listarPautas();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> removerPauta(@PathVariable Long id) throws Exception {

        pautaServicePort.removerPauta(id);

        return ResponseEntity.ok().build();
    }

}