package com.srossi.foro.infraestructura.errores;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String name = ex.getName(); // nombre del parámetro que causó el error
        String type = ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "desconocido";
        String value = ex.getValue() != null ? ex.getValue().toString() : "null";

        String message = String.format("El parámetro '%s' debe ser de tipo %s. Valor recibido: '%s'", name, type, value);

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    // Error 404: ID no existe en la base de datos
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNotFound(NoSuchElementException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("El tópico con el ID proporcionado no existe.");
    }
}
