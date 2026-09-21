package org.example.projectpetregister.entities.dtos;

import jakarta.validation.constraints.*;
import org.example.projectpetregister.entities.enums.Sex;
import org.example.projectpetregister.entities.enums.Type;

public record PetCreateDTO(

        @NotBlank
        @Pattern(regexp = "^[\\p{L}]+(?:[ -][\\p{L}]+)*$")
        String name,

        @NotBlank
        @Pattern(regexp = "^[\\p{L}]+(?:[ -][\\p{L}]+)*$")
        String lastName,

        @NotNull
        Type type,

        @NotNull
        Sex sex,

        @NotNull
        Integer homeNumber,

        @NotBlank
        @Pattern(regexp = "^[\\p{L}]+(?:[ -][\\p{L}]+)*$")
        String city,
        String address,

        @NotNull
        @DecimalMax("20.0")
        Double age,

        @NotNull
        @DecimalMin("0.5")
        @DecimalMax("60.0")
        Double weight,

        @NotBlank
        String breed
) {
}
