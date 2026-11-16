package com.example.productapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

/**
 * API dokümanının başlık, sürüm ve iletişim bilgilerini tanımlar.
 * /v3/api-docs altında JSON, /swagger-ui.html altında interaktif UI yayınlanır.
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Product API",
                version = "1.0.0",
                description = "Junior seviye Spring Boot çalışma projesi: springdoc-openapi ile dokümantasyon",
                contact = @Contact(name = "Tekinimm", url = "https://github.com/Tekinimm")
        )
)
public class OpenApiConfig {
}
