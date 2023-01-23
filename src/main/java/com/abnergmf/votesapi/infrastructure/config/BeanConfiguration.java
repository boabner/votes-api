package com.abnergmf.votesapi.infrastructure.config;

import com.abnergmf.votesapi.application.adapters.converter.PautaDTOConverter;
import com.abnergmf.votesapi.application.adapters.converter.SessaoResultadoDTOConverter;
import com.abnergmf.votesapi.application.adapters.converter.SessaoDTOConverter;
import com.abnergmf.votesapi.domain.adapters.services.PautaServiceImpl;
import com.abnergmf.votesapi.domain.adapters.services.SessaoServiceImpl;
import com.abnergmf.votesapi.domain.adapters.services.VotacaoServiceImpl;
import com.abnergmf.votesapi.domain.ports.interfaces.PautaServicePort;
import com.abnergmf.votesapi.domain.ports.interfaces.SessaoServicePort;
import com.abnergmf.votesapi.domain.ports.interfaces.VotacaoServicePort;
import com.abnergmf.votesapi.domain.ports.repositories.PautaRepositoryPort;
import com.abnergmf.votesapi.domain.ports.repositories.SessaoRepositoryPort;
import com.abnergmf.votesapi.domain.ports.repositories.VotacaoRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    PautaServicePort pautaService(PautaRepositoryPort pautaRepositoryPort, PautaDTOConverter pautaDTOConverter) {
        return new PautaServiceImpl(pautaRepositoryPort, pautaDTOConverter);
    }

    @Bean
    VotacaoServicePort votacaoService(VotacaoRepositoryPort votacaoRepositoryPort) {
        return new VotacaoServiceImpl(votacaoRepositoryPort);
    }

    @Bean
    SessaoServicePort sessaoService(SessaoRepositoryPort sessaoRepositoryPort, SessaoDTOConverter sessaoDTOConverter,
                                    SessaoResultadoDTOConverter sessaoAtivaDTOConverter) {
        return new SessaoServiceImpl(sessaoRepositoryPort, sessaoDTOConverter, sessaoAtivaDTOConverter);
    }

}
