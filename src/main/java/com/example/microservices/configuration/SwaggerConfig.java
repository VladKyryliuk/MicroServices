package com.example.microservices.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Documentation",
                description = "Swagger module",
                version = "1.0",
                contact = @Contact(
                        name = "Kyryliuk Vlad",
                        email = "vlad.kyryliukr@gmail.com",
                        url = "https://github.com/VladKyryliuk"
                )
        )
)

public class SwaggerConfig {
}
