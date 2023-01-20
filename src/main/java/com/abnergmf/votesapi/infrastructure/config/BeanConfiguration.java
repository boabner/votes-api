package com.abnergmf.votesapi.infrastructure.config;

import com.abnergmf.votesapi.application.adapters.converter.PautaDTOConverter;
import com.abnergmf.votesapi.domain.adapters.services.PautaServiceImpl;
import com.abnergmf.votesapi.domain.ports.interfaces.PautaServicePort;
import com.abnergmf.votesapi.domain.ports.repositories.PautaRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    PautaServicePort pautaService(PautaRepositoryPort pautaRepositoryPort, PautaDTOConverter pautaDTOConverter) {
        return new PautaServiceImpl(pautaRepositoryPort, pautaDTOConverter);
    }

}
