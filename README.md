# 📘 API del Foro en Spring Boot

Este proyecto es una API RESTful desarrollada con Spring Boot. Permite la gestión de tópicos, incluyendo creación, visualización, actualización y eliminación.

## 🧪 Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database (modo local)
- Springdoc OpenAPI (Swagger)

## 🔗 Documentación Swagger

Puedes acceder a la documentación Swagger generada automáticamente por Springdoc en:

[👉 Swagger UI](http://localhost:8081/swagger-ui/index.html)

## 🚀 Endpoints principales

| Método | URI                  | Descripción               |
|--------|----------------------|---------------------------|
| GET    | /topicos             | Lista todos los tópicos   |
| POST   | /topicos             | Crea un nuevo tópico      |
| PUT    | /topicos             | Actualiza un tópico       |
| DELETE | /topicos/{id}        | Elimina un tópico         |

## 📦 Cómo correr el proyecto

```bash
./mvnw spring-boot:run