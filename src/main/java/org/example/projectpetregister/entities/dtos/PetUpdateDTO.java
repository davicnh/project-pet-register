package org.example.projectpetregister.entities.dtos;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record PetUpdateDTO(

        @NotBlank
        @Pattern(regexp = "^[\\p{L}]+(?:[ -][\\p{L}]+)*$")
        String firstName,

        @NotBlank
        @Pattern(regexp = "^[\\p{L}]+(?:[ -][\\p{L}]+)*$")
        String lastName,

        @DecimalMax("20.0")
        Double age,

        @DecimalMin("0.5")
        @DecimalMax("60.0")
        Double weight,

        Integer homeNumber,

        @Pattern(regexp = "^[\\p{L}]+(?:[ -][\\p{L}]+)*$")
        String city,

        String address
) {
}
