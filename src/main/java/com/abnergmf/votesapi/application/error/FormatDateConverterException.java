package com.abnergmf.votesapi.application.error;

public class FormatDateConverterException extends GenericException {

    public FormatDateConverterException(Object data) {
        super(String.format("Não foi possível processar a data '%s'. Favor utilizar o padrão dd/MM/yyyy hh:MM:ss.", data));
    }

}