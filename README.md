# Product API — springdoc-openapi ile Dokümantasyon

Junior seviye Spring Boot çalışma projesi. Konu: **OpenAPI dokümantasyonu (springdoc-openapi)**.
Küçük bir ürün CRUD API'si üzerinde Swagger UI ve OpenAPI 3 şeması üretmeyi öğretir.

## Bu proje neyi öğretir?

- `springdoc-openapi-starter-webmvc-ui` bağımlılığı ile otomatik OpenAPI şeması üretimi
- `@Operation`, `@ApiResponse`, `@Parameter`, `@Tag` ve `@Schema` anotasyonlarıyla dokümanı zenginleştirme
- Bean Validation (`@NotBlank`, `@PositiveOrZero`) kurallarının şemaya yansıması
- `@RestControllerAdvice` + `ProblemDetail` (RFC 7807) ile standart hata yanıtları
- Katmanlı yapı: controller → service benzeri repository → model

## Nasıl çalıştırılır?

```bash
mvn spring-boot:run
```

Ardından tarayıcıda aç:

- Swagger UI: http://localhost:8080/swagger-ui.html
- Ham OpenAPI JSON: http://localhost:8080/v3/api-docs

## Testler

```bash
mvn test
```

`MockMvc` ile uçtan uca (endpoint → doğrulama → hata işleme) testler yazılmıştır.

## Çalışma notları

1. springdoc bağımlılığı eklenince dokümantasyon **otomatik** oluşur; anotasyonlar sadece
   açıklama ve örnek değer eklemek içindir.
2. `@Schema`'yı record bileşenlerine koyarak alan açıklamalarını şemaya taşıdık.
3. `GlobalExceptionHandler` bir `@RestControllerAdvice` olduğu için 404/400 yanıtları
   dokümanda otomatik görünür.
4. `ProblemDetail` kullanmak hata formatını standartlaştırır ve OpenAPI 3.0'ın
   `application/problem+json` desteğiyle uyumludur.
