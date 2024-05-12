package br.com.julio.javaintegrationfullstack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "br.com.julio.javaintegrationfullstack")
public class JavaIntegrationFullStackApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaIntegrationFullStackApplication.class, args);
	}

}
