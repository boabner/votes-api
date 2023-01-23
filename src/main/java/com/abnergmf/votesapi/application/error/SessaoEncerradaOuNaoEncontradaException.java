package com.abnergmf.votesapi.application.error;

public class SessaoEncerradaOuNaoEncontradaException extends RuntimeException {

    public SessaoEncerradaOuNaoEncontradaException(Long id) {
        super(String.format("Sessão com id %d não encontrada ou já encerrada.", id));
    }

}