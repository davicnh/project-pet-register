package org.example.projectpetregister.specification;

import org.example.projectpetregister.entities.Pet;
import org.example.projectpetregister.entities.enums.Sex;
import org.example.projectpetregister.entities.enums.Type;
import org.springframework.data.jpa.domain.Specification;

public class PetSpecification {

    public static Specification<Pet> nameContains(String name) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
    }

    public static Specification<Pet> breedContains(String breed) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("breed")), "%" + breed.toLowerCase() + "%"));
    }

    public static Specification<Pet> typeEquals(Type type) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("type"), type));
    }

    public static Specification<Pet> sexEquals(Sex sex) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("sex"), sex));
    }

    public static Specification<Pet> ageEquals(Double age) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("age"), age));
    }

    public static Specification<Pet> weightEquals(Double weight) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("weight"), weight));
    }

    public static Specification<Pet> cityContains(String city) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("city")), "%" + city.toLowerCase() + "%"));
    }

    public static Specification<Pet> addressContains(String address) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("address")), "%" + address.toLowerCase() + "%"));
    }
}
