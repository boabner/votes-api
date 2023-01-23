package com.abnergmf.votesapi.application.error;

public class VoteAPIObjectNotFoundException extends GenericException {

    public VoteAPIObjectNotFoundException(String nomeEntidade, Long id) {
        super(String.format("%s com id %d não encontrado.", nomeEntidade, id));
    }

}