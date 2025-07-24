package com.srossi.foro.controller;

import com.srossi.foro.dto.TopicoListadoDatos;
import com.srossi.foro.dto.TopicoRequest;
import com.srossi.foro.dto.TopicoResponse;
import com.srossi.foro.repository.TopicoRepository;
import com.srossi.foro.service.TopicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController //Indica que esta clase es un controlador de tipo REST. Combina @Controller y @ResponseBody, lo que significa que los métodos devolverán directamente datos (normalmente en formato JSON) como respuesta HTTP, en lugar de retornar una vista (HTML).
@RequestMapping("/topicos") //Define la ruta base para todos los endpoints dentro del controlador. En este caso, cualquier endpoint dentro de esta clase comenzará con /topicos. Por ejemplo, el @PostMapping definido será accesible vía POST /topicos.
@Tag(name = "Topicos", description = "API para gestionar tópicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;  //Indica a Spring que debe inyectar automáticamente la dependencia (en este caso, TopicoService). Esto permite que el controlador use el servicio sin tener que crear una instancia manualmente.

    private TopicoRepository topicoRepository;
    @PostMapping //Especifica que el méeodo registrar manejará solicitudes HTTP de tipo POST.
    //@RequestBody Indica que el parámetro request debe construirse a partir del cuerpo de la solicitud HTTP (normalmente JSON). Spring convierte automáticamente el JSON en una instancia de TopicoRequest.
    //@Valid Le dice a Spring que debe validar el objeto TopicoRequest antes de ejecutarse el meodo. Usa las anotaciones como @NotBlank o @NotNull dentro de TopicoRequest.
    //topicoService.registrarTopico(request) Llama al servicio que se encarga de la lógica de negocio para registrar el tópico, devolviendo la respuesta con los datos del nuevo tópico.
    @Operation(summary = "Registrar un nuevo tópico")
    public ResponseEntity<TopicoResponse> registrar(@RequestBody @Valid TopicoRequest request) {
        TopicoResponse response = topicoService.registrarTopico(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<TopicoListadoDatos>> listar(
            @RequestParam(required = false) String curso,
            @RequestParam(required = false) Integer anio,
            @PageableDefault(size = 10, sort = "fechaCreacion", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Page<TopicoListadoDatos> topicos;

        if (curso != null && anio != null) {
            topicos = topicoService.listarPorCursoYAnio(curso, anio, pageable);
        } else if (curso != null) {
            topicos = topicoService.listarPorCurso(curso, pageable);
        } else {
            topicos = topicoService.listarTopicos(pageable);
        }

        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/ping")
    public String ping() {
        System.out.println("Ping endpoint called");
        return "pong";
    }
}