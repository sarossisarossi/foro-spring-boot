package com.srossi.foro.service;

import com.srossi.foro.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//Servicio para la autentiacion
@Service
public class AutenticacionService implements UserDetailsService {
    //Agregar el repository
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String correoelectronico) throws UsernameNotFoundException {
        return usuarioRepository.findByCorreoElectronico(correoelectronico);
    }


}
