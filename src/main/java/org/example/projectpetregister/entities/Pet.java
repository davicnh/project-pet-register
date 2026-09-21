package org.example.projectpetregister.entities;

import jakarta.persistence.*;
import org.example.projectpetregister.entities.enums.Sex;
import org.example.projectpetregister.entities.enums.Type;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Pet implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    private Integer homeNumber;
    private String city;
    private String address;
    private Double age;
    private Double weight;
    private String breed;

    public Pet() {
    }

    public Pet(String name, String lastName, Type type, Sex sex, Integer homeNumber, String city, String address, Double age, Double weight, String breed) {
        this.name = name;
        this.lastName = lastName;
        this.type = type;
        this.sex = sex;
        this.homeNumber = homeNumber;
        this.city = city;
        this.address = address;
        this.age = age;
        this.weight = weight;
        this.breed = breed;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(Integer homeNumber) {
        this.homeNumber = homeNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        Pet pet = (Pet) o;
        return Objects.equals(id, pet.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
