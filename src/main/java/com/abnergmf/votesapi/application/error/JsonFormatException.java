package com.abnergmf.votesapi.application.error;

public class JsonFormatException extends GenericException {

    public JsonFormatException(Object data) {
        super(String.format("Não foi possível processar o campo '%s'.", data));
    }

}