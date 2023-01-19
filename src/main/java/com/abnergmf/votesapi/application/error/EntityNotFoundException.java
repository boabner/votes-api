package com.abnergmf.votesapi.application.error;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String nomeEntidade, Long id) {
        super(String.format("%s com id %d não encontrado.", nomeEntidade, id));
    }

}