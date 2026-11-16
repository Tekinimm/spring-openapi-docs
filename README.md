# Product API — API Documentation with springdoc-openapi

A junior-level Spring Boot study project. Topic: **OpenAPI documentation with springdoc-openapi**.
It teaches how to produce a Swagger UI and an OpenAPI 3 schema on top of a small product CRUD API.

## What this project teaches

- Automatic OpenAPI schema generation with the `springdoc-openapi-starter-webmvc-ui` dependency
- Enriching documentation with `@Operation`, `@ApiResponse`, `@Parameter`, `@Tag` and `@Schema` annotations
- How Bean Validation rules (`@NotBlank`, `@PositiveOrZero`) are reflected into the schema
- Standard error responses with `@RestControllerAdvice` + `ProblemDetail` (RFC 7807)
- Layered structure: controller → repository → model

## How to run

```bash
mvn spring-boot:run
```

Then open in your browser:

- Swagger UI: http://localhost:8080/swagger-ui.html
- Raw OpenAPI JSON: http://localhost:8080/v3/api-docs

## Tests

```bash
mvn test
```

End-to-end tests (endpoint → validation → error handling) are written with `MockMvc`.

## Study notes

1. Once the springdoc dependency is added, documentation is generated **automatically**;
   annotations only add descriptions and example values.
2. `@Schema` on record components carries field descriptions into the schema.
3. `GlobalExceptionHandler` is a `@RestControllerAdvice`, so 404/400 responses
   appear in the documentation automatically.
4. Using `ProblemDetail` standardizes the error format and aligns with OpenAPI 3.0's
   `application/problem+json` support.
