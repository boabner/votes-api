package com.abnergmf.votesapi.application.error;

public class NullObjectVotesAPIException extends RuntimeException {

    public NullObjectVotesAPIException(String nomeObjeto, String nomeClasse) {
        super(String.format("Tentantiva de processar um objeto nulo do tipo '%s' em .", nomeObjeto));
    }

}