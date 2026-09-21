package org.example.projectpetregister.entities.dtos;

public record PetUpdateDTO(String firstName, String lastName, int age, double weight, int homeNumber, String city, String address) {
}
