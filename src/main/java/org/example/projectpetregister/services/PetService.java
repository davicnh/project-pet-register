package org.example.projectpetregister.services;

import jakarta.transaction.Transactional;
import org.example.projectpetregister.entities.Pet;
import org.example.projectpetregister.entities.dtos.PetCreateDTO;
import org.example.projectpetregister.entities.dtos.PetSearchDTO;
import org.example.projectpetregister.entities.dtos.PetUpdateDTO;
import org.example.projectpetregister.exceptions.InvalidSearchException;
import org.example.projectpetregister.exceptions.ResourceNotFoundException;
import org.example.projectpetregister.repositories.PetRepository;
import org.example.projectpetregister.specification.PetSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    public Pet savePet(PetCreateDTO dto) {

        Pet pet = new Pet();
        pet.setName(dto.name());
        pet.setLastName(dto.lastName());
        pet.setType(dto.type());
        pet.setSex(dto.sex());
        pet.setHomeNumber(dto.homeNumber());
        pet.setCity(dto.city());
        pet.setAddress(dto.address());
        pet.setAge(dto.age());
        pet.setWeight(dto.weight());
        pet.setBreed(dto.breed());

        Pet savePet = petRepository.save(pet);
        return savePet;
    }

    public List<Pet> findAllPets() {
        return petRepository.findAll();
    }

    public void deletePet(UUID id) {

        if (!petRepository.existsById(id)) {
            throw new ResourceNotFoundException("Pet não encontrado");
        }

        petRepository.deleteById(id);
    }

    public Pet findPetById(UUID id) {
        if (!petRepository.existsById(id)) {
            throw new ResourceNotFoundException("Pet não encontrado");
        }
        return petRepository.findById(id).get();
    }

    @Transactional
    public void updatePet(UUID id, PetUpdateDTO dto) {

        Pet pet = petRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pet não encontrado"));

        pet.setName(dto.firstName());
        pet.setLastName(dto.lastName());
        pet.setWeight(dto.weight());
        pet.setAddress(dto.address());
        pet.setCity(dto.city());
        pet.setHomeNumber(Integer.valueOf(dto.homeNumber()));
        pet.setAge(dto.age());

        petRepository.save(pet);
    }

    public List<Pet> findPets(PetSearchDTO dto) {
        Specification<Pet> spec = null;

        int criteriaCont = 0;

        if (dto.name() != null) {
            criteriaCont++;
            spec = spec == null
                    ? PetSpecification.nameContains(dto.name())
                    : spec.and(PetSpecification.nameContains(dto.name()));
        }

        if (dto.breed() != null) {
            criteriaCont++;
            spec = spec == null
                    ? PetSpecification.breedContains(dto.breed())
                    : spec.and(PetSpecification.breedContains(dto.breed()));
        }

        if (dto.city() != null) {
            criteriaCont++;
            spec = spec == null
                    ? PetSpecification.cityContains(dto.city())
                    : spec.and(PetSpecification.cityContains(dto.city()));
        }

        if (dto.address() != null) {
            criteriaCont++;
            spec = spec == null
                    ? PetSpecification.addressContains(dto.address())
                    : spec.and(PetSpecification.addressContains(dto.address()));
        }

        if (dto.age() != null) {
            criteriaCont++;
            spec = spec == null
                    ? PetSpecification.ageEquals(dto.age())
                    : spec.and(PetSpecification.ageEquals(dto.age()));
        }

        if (dto.weight() != null) {
            criteriaCont++;
            spec = spec == null
                    ? PetSpecification.weightEquals(dto.weight())
                    : spec.and(PetSpecification.weightEquals(dto.weight()));
        }

        if (dto.type() != null) {
            criteriaCont++;
            spec = spec == null
                    ? PetSpecification.typeEquals(dto.type())
                    : spec.and(PetSpecification.typeEquals(dto.type()));
        }

        if (dto.sex() != null) {
            criteriaCont++;
            spec = spec == null
                    ? PetSpecification.sexEquals(dto.sex())
                    : spec.and(PetSpecification.sexEquals(dto.sex()));
        }

        if (criteriaCont > 2) {
            throw new InvalidSearchException("Número de critérios maior que o permitido.");
        }

        return spec == null
                ? petRepository.findAll()
                : petRepository.findAll(spec);
    }
}
