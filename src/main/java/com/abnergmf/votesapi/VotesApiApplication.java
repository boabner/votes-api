package com.abnergmf.votesapi;

import com.abnergmf.votesapi.infrastructure.adapters.repositories.PautaRepositoryDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = PautaRepositoryDAO.class)
public class VotesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotesApiApplication.class, args);
	}

}
