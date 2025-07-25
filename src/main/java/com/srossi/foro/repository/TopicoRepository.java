package com.srossi.foro.repository;

import com.srossi.foro.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsByTituloAndMensaje(String titulo, String mensaje);
    // Buscar tópicos por nombre de curso (exacto) y paginar
    Page<Topico> findByCurso_NombreIgnoreCase(String cursoNombre, Pageable pageable);

    // Buscar tópicos por año en fechaCreacion y paginar
    Page<Topico> findByFechaCreacionBetween(LocalDateTime desde, LocalDateTime hasta, Pageable pageable);

    // Buscar tópicos por curso y rango fecha (año) paginados
    Page<Topico> findByCurso_NombreIgnoreCaseAndFechaCreacionBetween(
            String cursoNombre,
            LocalDateTime desde,
            LocalDateTime hasta,
            Pageable pageable
    );
    Page<Topico> findByCursoNombre(String curso, Pageable pageable);
}

