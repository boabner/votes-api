package com.abnergmf.votesapi.application.error;

public class PautaAlreadyStartedException extends GenericException {

    public PautaAlreadyStartedException(Long id) {
        super(String.format("Pauta %d já iniciada em uma sessão de votação.", id));
    }

}