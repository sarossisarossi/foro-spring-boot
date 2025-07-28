package com.srossi.foro.controller;

import com.srossi.foro.dto.DatosActualizarTopico;
import com.srossi.foro.dto.TopicoListadoDatos;
import com.srossi.foro.dto.TopicoRequest;
import com.srossi.foro.dto.TopicoResponse;
import com.srossi.foro.model.Topico;
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

import java.util.List;

@RestController //Indica que esta clase es un controlador de tipo REST. Combina @Controller y @ResponseBody, lo que significa que los métodos devolverán directamente datos (normalmente en formato JSON) como respuesta HTTP, en lugar de retornar una vista (HTML).
@RequestMapping("/topicos") //Define la ruta base para todos los endpoints dentro del controlador. En este caso, cualquier endpoint dentro de esta clase comenzará con /topicos. Por ejemplo, el @PostMapping definido será accesible vía POST /topicos.
@Tag(name = "Topicos", description = "API para gestionar tópicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;  //Indica a Spring que debe inyectar automáticamente la dependencia (en este caso, TopicoService). Esto permite que el controlador use el servicio sin tener que crear una instancia manualmente.

    private TopicoRepository topicoRepository;
    //Esa es una inyección de dependencias mediante constructor. En palabras simples:
    //
    //Le estás diciendo a Spring: “Cuando crees una instancia de este TopicoController, pásame automáticamente una instancia de TopicoRepository para que yo la pueda usar.”
    @Autowired
    public TopicoController(TopicoRepository topicoRepository) {
        this.topicoRepository = topicoRepository;
    }
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
    public List<TopicoListadoDatos> listar(){
        List<Topico> topicos = topicoRepository.findAll();
        System.out.println("Cantidad de tópicos: " + topicos.size());
        return topicoRepository.findAll().stream().map(TopicoListadoDatos::new).toList();
    }

//    @GetMapping("/a{id}")
//    public ResponseEntity<TopicoListadoDatos> detalle(@PathVariable Long id) {
//        var topico = topicoRepository.findById(id);
//        if (topico.isPresent()) {
//            return ResponseEntity.ok(new TopicoListadoDatos(topico.get()));
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @GetMapping("/{id}")
    public TopicoListadoDatos obtenerTopicoPorId(@PathVariable Long id) {
        Topico topico = topicoService.buscarPorId(id);
        return new TopicoListadoDatos(topico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico datos) {
        try {
            boolean actualizado = topicoService.actualizar(id, datos);
            if (actualizado) {
                return ResponseEntity.ok("Registro actualizado OK");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID no encontrado");
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("El ID debe ser un número");
        }

    }

    @DeleteMapping("/topicos/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        topicoService.eliminarTopico(id);
        return ResponseEntity.noContent().build(); // 204
    }


    @GetMapping("/ping")
    public String ping() {
        System.out.println("Ping endpoint called");
        return "pong";
    }
}