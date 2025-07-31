# 📘 API del Foro en Spring Boot

Este proyecto es una API RESTful desarrollada con Spring Boot. Permite la gestión de tópicos, incluyendo creación, visualización, actualización y eliminación. Ahora incluye autenticación y autorización basada en tokens JWT, lo que restringe el acceso a ciertos endpoints.

## 🧪 Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- H2 Database (modo local)
- Springdoc OpenAPI (Swagger)
- JSON Web Tokens (JWT)

## 🔐 Seguridad

La API ahora cuenta con seguridad mediante autenticación basada en JWT. Los endpoints están protegidos, y solo los usuarios autenticados pueden acceder a los recursos principales.

### Endpoints públicos (sin token)

| Método | URI       | Descripción             |
|--------|-----------|-------------------------|
| POST   | /login    | Autenticación de usuario|

### Endpoints protegidos (requiere token JWT)

| Método | URI                  | Descripción               |
|--------|----------------------|---------------------------|
| GET    | /topicos             | Lista todos los tópicos   |
| POST   | /topicos             | Crea un nuevo tópico      |
| PUT    | /topicos             | Actualiza un tópico       |
| DELETE | /topicos/{id}        | Elimina un tópico         |

### Cómo usar el token JWT

1. Haz una petición `POST` a `/login` con tus credenciales.
2. Recibirás un token JWT en la respuesta.
3. Usa ese token en el header de tus siguientes peticiones:

```http
Authorization: Bearer <token>