package com.abnergmf.votesapi.application.adapters.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.abnergmf.votesapi.application.adapters.controller.form.VotacaoForm;
import com.abnergmf.votesapi.application.adapters.converter.VotacaoDTOConverter;
import com.abnergmf.votesapi.application.error.SessaoEncerradaOuNaoEncontradaException;
import com.abnergmf.votesapi.domain.dtos.ResultadoVotacaoDTO;
import com.abnergmf.votesapi.domain.dtos.VotacaoDTO;
import com.abnergmf.votesapi.domain.ports.interfaces.SessaoServicePort;
import com.abnergmf.votesapi.domain.ports.interfaces.VotacaoServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("votacao")
public class VotacaoController {

    @Autowired
    private VotacaoServicePort votacaoServicePort;
    @Autowired
    private VotacaoDTOConverter votacaoFormConverter;
    @Autowired
    private SessaoServicePort sessaoServicePort;

    @PostMapping("/registrar-voto")
    @Transactional
    public ResponseEntity<VotacaoDTO> registrarVoto(
            @RequestBody @Valid VotacaoForm votacaoForm
    ) {

        VotacaoDTO votacaoDTO = votacaoFormConverter.toVotacaoDTO(votacaoForm);

        if (sessaoServicePort.verificarSessaoAberta(votacaoDTO.getSessaoId())) {
            votacaoServicePort.registrarVoto(votacaoDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        throw new SessaoEncerradaOuNaoEncontradaException(votacaoDTO.getSessaoId());
    }

    @GetMapping("/resultado/sessao={sessaoId}")
    ResponseEntity<?> getResultadoVotacao(@PathVariable Long sessaoId) {
        ResultadoVotacaoDTO resultadoVotacaoDTO = votacaoServicePort.exibirResultadoPorSessaoId(sessaoId);
        return ResponseEntity.ok(resultadoVotacaoDTO);
    }

}