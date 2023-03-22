package com.optimal.solutions.springsample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
public class SpringsampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringsampleApplication.class, args);
	}

	/**
	 * This method sets some description for Open API documentation
	 * http://localhost:8080/swagger-ui/index.html
	 * @return
	 */
	@Bean
	public OpenAPI customOpenAPI() {

		return new OpenAPI().info(new Info()
						.title("My Sample Application API")
						.version("1.0")
						.description("Example API for Employees"));
			//          .termsOfService("http://swagger.io/terms/")
			//          .license(new License().name("Apache 2.0").url("http://springdoc.org")));

	}
}
