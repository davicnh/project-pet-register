package org.example.projectpetregister.services;

import org.example.projectpetregister.entities.Pet;
import org.example.projectpetregister.entities.dtos.PetCreateDTO;
import org.example.projectpetregister.entities.dtos.PetSearchDTO;
import org.example.projectpetregister.entities.dtos.PetUpdateDTO;
import org.example.projectpetregister.entities.enums.Sex;
import org.example.projectpetregister.entities.enums.Type;
import org.example.projectpetregister.exceptions.InvalidSearchException;
import org.example.projectpetregister.exceptions.ResourceNotFoundException;
import org.example.projectpetregister.repositories.PetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PetServiceTest {

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetService petService;

    @Test
    void findPetById() {

        UUID petId = UUID.randomUUID();
        Pet pet = new Pet();

        when(petRepository.existsById(petId)).thenReturn(true);
        when(petRepository.findById(petId)).thenReturn(Optional.of(pet));

        Pet result = petService.findPetById(petId);

        assertEquals(pet, result);
    }

    @Test
    void findPetByIdNotFound() {
        UUID petId = UUID.randomUUID();
        when(petRepository.existsById(petId)).thenReturn(false);
        assertThrows(ResourceNotFoundException.class, () -> {
            petService.findPetById(petId);
        });
    }

    @Test
    void deletePetById() {
        UUID petId = UUID.randomUUID();
        when(petRepository.existsById(petId)).thenReturn(true);
        petService.deletePet(petId);
        verify(petRepository).deleteById(petId);
    }

    @Test
    void deletePetByIdNotFound() {
        UUID petId = UUID.randomUUID();
        when(petRepository.existsById(petId)).thenReturn(false);
        assertThrows(ResourceNotFoundException.class, () -> {
            petService.deletePet(petId);
        });
        verify(petRepository, never()).deleteById(petId);
    }

    @Test
    void savePet() {
        PetCreateDTO dto = new PetCreateDTO(
                "Rex",
                "Silva",
                Type.DOG,
                Sex.MALE,
                100,
                "Blumenau",
                "Rua X",
                3.0,
                10.0,
                "Labrador"
        );
        Pet savedPet = new Pet();
        when(petRepository.save(any(Pet.class))).thenReturn(savedPet);
        Pet result = petService.savePet(dto);
        assertEquals(savedPet, result);
    }

    @Test
    void updatePet() {
        UUID petId = UUID.randomUUID();

        Pet pet = new Pet();

        PetUpdateDTO dto = new PetUpdateDTO(
                "Rex",
                "Silva",
                5.0,
                12.0,
                100,
                "Blumenau",
                "Rua X"
        );

        when(petRepository.findById(petId)).thenReturn(Optional.of(pet));

        petService.updatePet(petId, dto);

        verify(petRepository).save(pet);

        assertEquals("Rex", pet.getName());
        assertEquals("Silva", pet.getLastName());
        assertEquals(5.0, pet.getAge());
        assertEquals(12.0, pet.getWeight());
        assertEquals(100, pet.getHomeNumber());
        assertEquals("Blumenau", pet.getCity());
        assertEquals("Rua X", pet.getAddress());
    }

    @Test
    void updatePetNotFound() {
        UUID petId = UUID.randomUUID();

        PetUpdateDTO dto = new PetUpdateDTO(
                "Rex",
                "Silva",
                5.0,
                12.0,
                100,
                "Blumenau",
                "Rua X"
        );

        when(petRepository.findById(petId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            petService.updatePet(petId, dto);
        });

        verify(petRepository, never()).save(any(Pet.class));
    }

    @Test
    void findPetsWithMoreThanTwoCriteria() {
        PetSearchDTO dto = new PetSearchDTO(
                "Rex",
                Type.DOG,
                Sex.MALE,
                null,
                null,
                null,
                null,
                null
        );

        assertThrows(InvalidSearchException.class, () -> {
            petService.findPets(dto);
        });

        verify(petRepository, never()).findAll(any(Specification.class));
    }
}
