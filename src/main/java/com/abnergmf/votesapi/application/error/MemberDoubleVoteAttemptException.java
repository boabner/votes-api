package com.abnergmf.votesapi.application.error;

public class MemberDoubleVoteAttemptException extends GenericException {

    public MemberDoubleVoteAttemptException(Long id) {
        super(String.format("O associado de id %d já votou na sessão aberta para essa pauta.", id));
    }

}