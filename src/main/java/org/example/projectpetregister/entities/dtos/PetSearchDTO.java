package org.example.projectpetregister.entities.dtos;

import org.example.projectpetregister.entities.enums.Sex;
import org.example.projectpetregister.entities.enums.Type;

public record PetSearchDTO(
        String name,
        Type type,
        Sex sex,
        Double age,
        Double weight,
        String breed,
        String city,
        String address
) {
}
