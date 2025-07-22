package com.srossi.foro.service;

import com.srossi.foro.dto.TopicoRequest;
import com.srossi.foro.dto.TopicoResponse;
import com.srossi.foro.model.Curso;
import com.srossi.foro.model.Topico;
import com.srossi.foro.model.Usuario;
import com.srossi.foro.repository.CursoRepository;
import com.srossi.foro.repository.TopicoRepository;
import com.srossi.foro.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public TopicoResponse registrarTopico(@Valid TopicoRequest request) {
        if (topicoRepository.existsByTituloAndMensaje(request.titulo(), request.mensaje())) {
            throw new RuntimeException("Tópico duplicado");
        }

        Usuario autor = usuarioRepository.findById(request.autorId())
                .orElseThrow(() -> new RuntimeException("Autor no encontrado"));

        Curso curso = cursoRepository.findById(request.cursoId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        Topico topico = Topico.builder()
                .titulo(request.titulo())
                .mensaje(request.mensaje())
                .autor(autor)
                .curso(curso)
                .status(request.status())
                .build();

        topicoRepository.save(topico);

        return new TopicoResponse(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus()
        );
    }
}