package com.abnergmf.votesapi.application.error;

public class DuplicateSessionOpenAttemptException extends GenericException {

    public DuplicateSessionOpenAttemptException(Long id, Long pautaId) {
        super(String.format("Não é possível gerar outra sessão para a pauta de id %d, pois a sessão de id %d já foi gerada.", pautaId, id));
    }

}