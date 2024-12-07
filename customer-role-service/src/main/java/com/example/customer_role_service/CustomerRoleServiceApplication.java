package com.example.customer_role_service;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//OpenAPI provides a clear way to describe the API, making it easier to use,
// and the dependency helps automatically generate that description for the
// Java Spring applications. This makes the API more user-friendly for other developers!

@OpenAPIDefinition(
		info = @Info(
				title = "Customer Role Service API",
				version = "1.0",
				description = "API for managing customers' roles, including endpoints for adding and retrieving customers."
		)
)
public class CustomerRoleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerRoleServiceApplication.class, args);
	}

}
