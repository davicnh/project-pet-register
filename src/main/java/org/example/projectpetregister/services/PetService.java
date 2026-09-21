package org.example.projectpetregister.services;

import jakarta.transaction.Transactional;
import org.example.projectpetregister.entities.Pet;
import org.example.projectpetregister.entities.dtos.PetCreateDTO;
import org.example.projectpetregister.entities.dtos.PetUpdateDTO;
import org.example.projectpetregister.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    public Pet savePet(PetCreateDTO dto) {
        dto.name()
    }

    public List<Pet> findAllPets() {
        return petRepository.findAll();
    }

    public void deletePet(UUID id) {

        if (!petRepository.existsById(id)) {
            throw new RuntimeException("Pet não encontrado");
        }

        petRepository.deleteById(id);
    }

    public Pet findPetById(UUID id) {
        if (!petRepository.existsById(id)) {
            throw new RuntimeException("Pet não encontrado");
        }
        return petRepository.findById(id).get();
    }

    @Transactional
    public void updatePet(UUID id, PetUpdateDTO dto) {

        Pet pet = petRepository.findById(id).orElseThrow(() -> new RuntimeException("Pet não encontrado"));

        pet.setName(dto.firstName());
        pet.setLastName(dto.lastName());
        pet.setWeight(dto.weight());
        pet.setAddress(dto.address());
        pet.setCity(dto.city());
        pet.setHomeNumber(Integer.valueOf(dto.homeNumber()));
        pet.setAge(dto.age());

        petRepository.save(pet);
    }
}
