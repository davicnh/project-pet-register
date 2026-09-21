package org.example.projectpetregister.controllers;

import org.example.projectpetregister.entities.Pet;
import org.example.projectpetregister.entities.dtos.PetUpdateDTO;
import org.example.projectpetregister.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService petService;


    @PostMapping
    public ResponseEntity<Pet> postPet(@RequestBody Pet pet) {
        Pet savingPet = petService.savePet(pet);
        return ResponseEntity.status(HttpStatus.CREATED).body(savingPet);
    }

    @GetMapping
    public ResponseEntity<List<Pet>> getAllPets() {
        List<Pet> pets = petService.findAllPets();

        if (pets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok(pets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable UUID id) {
        Pet pet = petService.findPetById(id);
        if (pet == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(pet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putPet(@PathVariable UUID id, @RequestBody PetUpdateDTO dto) {
        petService.updatePet(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable UUID id) {
        petService.deletePet(id);
        return ResponseEntity.noContent().build();
    }
}
