package com.example.productapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

/**
 * Defines the title, version and contact information of the API documentation.
 * JSON is served under /v3/api-docs and the interactive UI under /swagger-ui.html.
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Product API",
                version = "1.0.0",
                description = "Junior-level Spring Boot study project: API documentation with springdoc-openapi",
                contact = @Contact(name = "Tekinimm", url = "https://github.com/Tekinimm")
        )
)
public class OpenApiConfig {
}
