package com.abnergmf.votesapi.application.error;

public class NullObjectVotesAPIException extends RuntimeException {

    public NullObjectVotesAPIException(String nomeObjeto) {
        super(String.format("Tentantiva de processar um objeto nulo do tipo '%s'.", nomeObjeto));
    }

}