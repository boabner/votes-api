package com.abnergmf.votesapi.application.error;

public class JsonFormatException extends RuntimeException {

    public JsonFormatException(Object data) {
        super(String.format("Não foi possível processar o campo '%s'.", data));
    }

}