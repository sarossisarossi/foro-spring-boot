package com.srossi.foro.service;

import com.srossi.foro.dto.TopicoListadoDatos;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
                .fechaCreacion(LocalDateTime.now())
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

    public Page<TopicoListadoDatos> listarTopicos(Pageable pagina) {
        Page<Topico> topicos = topicoRepository.findAll(pagina);

        return topicos.map(topico -> new TopicoListadoDatos(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre()
        ));
    }



    public Page<TopicoListadoDatos> listarPorCurso(String curso, Pageable pageable) {
        Page<Topico> topicosFiltrados = topicoRepository.findByCursoNombre(curso, pageable);

        return topicosFiltrados.map(topico -> new TopicoListadoDatos(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre()
        ));
    }

    public Page<TopicoListadoDatos> listarPorCursoYAnio(String curso, Integer anio, Pageable pageable) {
        return null;}
}