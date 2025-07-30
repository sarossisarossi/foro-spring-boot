package com.srossi.foro.controller;

import com.srossi.foro.dto.DatosAutenticacion;
import com.srossi.foro.dto.DatosTokenJWT;
import com.srossi.foro.model.Usuario;
import com.srossi.foro.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity iniciarSecion(@RequestBody @Valid DatosAutenticacion datos){
        var authenticatonToken = new UsernamePasswordAuthenticationToken(datos.correoElectronico(),datos.contrasena());
        var autenticacion = manager.authenticate(authenticatonToken);

        var tokenJWT = tokenService.generarToken((Usuario) autenticacion.getPrincipal());
        return ResponseEntity.ok(new DatosTokenJWT(tokenJWT));
    }
}
