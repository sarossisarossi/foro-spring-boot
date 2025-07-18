package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
@SpringBootApplication

public class Main { // Puedes renombrar esta clase a 'ForoApplication' o similar si lo deseas

    public static void main(String[] args) {
        // Este método arranca la aplicación Spring Boot.
        // Aquí es donde Spring Boot lee application.properties,
        // configura el datasource y ejecuta Flyway.
        SpringApplication.run(Main.class, args);

        // Los System.out.println que tenías son de una aplicación Java normal y no son necesarios aquí,
        // a menos que quieras que se ejecuten después de que la aplicación Spring Boot haya arrancado.
        // Para probar la base de datos, simplemente inicia la app y revisa tu BD.
        System.out.println("La aplicación Spring Boot ha iniciado.");
    }
}