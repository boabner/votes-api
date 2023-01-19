package com.abnergmf.votesapi.application.error;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super(String.format("Usuário id %d não encontrado.", id));
    }

}