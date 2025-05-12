package com.dao;

import com.entity.Pet;
import java.util.List;

public interface IPetService {
    void addPet(Pet pet);
    List<Pet> getAllPets();
    void recordCashDonation(String donorName, double amount);
    void registerParticipant(String participantName, int eventId);
    void recordItemDonation(String donorName, String itemType, double value);

}