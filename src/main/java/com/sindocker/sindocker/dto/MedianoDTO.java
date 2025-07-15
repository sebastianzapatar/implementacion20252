package com.sindocker.sindocker.dto;


import jakarta.validation.constraints.NotBlank;

public record MedianoDTO(
        @NotBlank(message = "Ingresar nombre") String nombre,
        double altura) {
}
