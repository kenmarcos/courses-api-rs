package br.com.kenmarcos.courses_api_rs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
	info = @Info(
		title = "Courses API",
		description = "API fictícia para uma empresa de cursos de programação.",
		version = "1"
	)
)
public class CoursesApiRsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoursesApiRsApplication.class, args);
	}

}
