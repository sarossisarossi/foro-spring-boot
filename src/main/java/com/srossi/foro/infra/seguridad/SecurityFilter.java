package com.srossi.foro.infra.seguridad;

import com.srossi.foro.repository.UsuarioRepository;
import com.srossi.foro.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.lang.ref.SoftReference;

//algo gnerico que spring cargue
@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.println("Filtr LLAMADO");
        //validacion token
        //token viene del header
        var tokenJWT = recuperarToken(request);
        //System.out.println(tokenJWT);
        // continua la cadena
        if (tokenJWT !=null) {
            var subject = tokenService.getSubject(tokenJWT);
            System.out.println("SUBJECT DEL TOKEN: " + subject);
            var usuario = usuarioRepository.findByCorreoElectronico(subject);
            if (usuario == null) {
                System.out.println("USUARIO NO ENCONTRADO EN BD PARA EL SUBJECT");
            } else {
                System.out.println("USUARIO ENCONTRADO: " + usuario.getUsername());
            }
            var authentication = new UsernamePasswordAuthenticationToken(usuario, null,usuario.getAuthorities());
            // este codigo indica que esta logeado
            System.out.println(authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("Usuario logeado");
            System.out.println("Authorization header: " + request.getHeader("Authorization"));
            System.out.println("Authorities: " + usuario.getAuthorities());
        }
        //System.out.println(subject);

        filterChain.doFilter(request,response);

    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null){
            return authorizationHeader.substring(7);

        }
        return null;

    }

}
