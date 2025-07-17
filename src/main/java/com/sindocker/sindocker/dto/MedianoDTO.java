package com.sindocker.sindocker.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record MedianoDTO(
        @NotBlank(message = "Ingresar nombre")
        String nombre,
        @Min(100)
        @Max(140)
        long altura,
        @Email(message="Coloca un email")
        @NotBlank(message="Email obligatorio")
        String email) {
}
