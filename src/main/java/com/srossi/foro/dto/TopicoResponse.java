package com.srossi.foro.dto;

import java.time.LocalDateTime;

public record TopicoResponse(Long id,
                             String titulo,
                             String mensaje,
                             LocalDateTime fechaCreacion,
                             String status) {
}
