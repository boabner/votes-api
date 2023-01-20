package com.abnergmf.votesapi.application.error;

public class FormatDateConverterException extends RuntimeException {

    public FormatDateConverterException(Object data) {
        super(String.format("Não foi possível processar a data '%s'.", data));
    }

}