package com.srossi.foro.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicoRequest(@NotBlank String titulo,
                            @NotBlank String mensaje,
                            @NotNull Long autorId,
                            @NotNull Long cursoId,
                            @NotBlank String status)
{
}
