package com.abnergmf.votesapi.application.error;

import javax.annotation.PostConstruct;

import com.abnergmf.votesapi.infrastructure.adapters.repositories.PautaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericException extends RuntimeException {

    private static final Logger logger = LoggerFactory.getLogger(PautaRepository.class.getName());

    public GenericException(String message) {
        super(message);
        logger.info(message);
    }

}
