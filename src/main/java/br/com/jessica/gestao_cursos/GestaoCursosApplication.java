package br.com.jessica.gestao_cursos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Cursos", description = "API responsavel pelos cursos", version = "1"))
public class GestaoCursosApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoCursosApplication.class, args);
	}

}
