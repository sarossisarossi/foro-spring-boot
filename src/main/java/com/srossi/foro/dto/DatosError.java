package com.srossi.foro.dto;

import java.time.LocalDateTime;

public record DatosError(int codigo, String mensaje, LocalDateTime timestamp) {}
