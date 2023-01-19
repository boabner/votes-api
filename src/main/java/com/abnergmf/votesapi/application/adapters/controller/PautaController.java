package com.abnergmf.votesapi.application.adapters.controller;

import java.util.List;

import com.abnergmf.votesapi.application.adapters.controller.form.PautaForm;
import com.abnergmf.votesapi.domain.Pauta;
import com.abnergmf.votesapi.domain.dtos.PautaDTO;
import com.abnergmf.votesapi.domain.ports.interfaces.PautaServicePort;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(PautaController.class.getName());
    private final PautaServicePort pautaServicePort;

    public PautaController(PautaServicePort pautaServicePort) {
        this.pautaServicePort = pautaServicePort;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PautaDTO> criarPauta(
        @RequestBody @Valid PautaForm pautaForm
    ) {
        Pauta pauta = pautaServicePort.criarPauta(pautaForm.toPautaDTO());

        logger.info("Pauta \"" + pauta.getNome() + "\" criada na base.");

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualizarPauta(@PathVariable Long id, @RequestBody @Valid PautaForm pautaForm) throws Exception{

        Pauta pauta = pautaServicePort.atualizarPauta(id, pautaForm.toPautaDTO());

        logger.info("Pauta \"" + id + "\" atualizada na base.");

        return ResponseEntity.ok(pauta);
    }

    @GetMapping
    List<PautaDTO> getPautas() {
        return pautaServicePort.listarPautas();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) throws Exception {

        pautaServicePort.removerPauta(id);

        logger.info("Pauta \"" + id + "\" removida da base.");

        return ResponseEntity.ok().build();
    }

}
