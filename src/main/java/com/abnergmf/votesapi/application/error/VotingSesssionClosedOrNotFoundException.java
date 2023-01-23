package com.abnergmf.votesapi.application.error;

public class VotingSesssionClosedOrNotFoundException extends GenericException {

    public VotingSesssionClosedOrNotFoundException(Long id) {
        super(String.format("Sessão com id %d não encontrada ou já encerrada.", id));
    }

}