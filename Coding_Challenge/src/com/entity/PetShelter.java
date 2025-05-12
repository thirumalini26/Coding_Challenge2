package com.entity;

import java.util.ArrayList;
import java.util.List;

public class PetShelter {
    private List<Pet> availablePets = new ArrayList<>();

    public void addPet(Pet pet) {
        availablePets.add(pet);
    }

    public void removePet(Pet pet) {
        availablePets.remove(pet);
    }

    public void listAvailablePets() {
        if (availablePets.isEmpty()) {
            System.out.println("No pets available.");
            return;
        }
        for (Pet pet : availablePets) {
            try {
                System.out.println(pet.toString());
            } catch (NullPointerException e) {
                System.out.println("Pet data is incomplete.");
            }
        }
    }

    public List<Pet> getAvailablePets() {
        return availablePets;
    }
}
