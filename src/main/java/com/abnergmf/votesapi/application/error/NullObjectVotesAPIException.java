package com.abnergmf.votesapi.application.error;

public class NullObjectVotesAPIException extends GenericException {

    public NullObjectVotesAPIException(String nomeObjeto) {
        super(String.format("Tentantiva de processar um objeto nulo do tipo '%s'.", nomeObjeto));
    }

}