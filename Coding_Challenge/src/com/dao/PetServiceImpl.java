package com.dao;

import com.entity.Pet;
import java.sql.*;
import java.util.*;
import com.util.DBConnUtil;

public class PetServiceImpl implements IPetService {
    private Connection conn;

    public PetServiceImpl() {
        conn = DBConnUtil.getConnection("resources/db.properties");
    }

    @Override
    public void addPet(Pet pet) {
        try {
            String query = "INSERT INTO pets (name, age, breed, pet_type, extra_info) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, pet.getName());
            stmt.setInt(2, pet.getAge());
            stmt.setString(3, pet.getBreed());

            if (pet instanceof com.entity.Dog) {
                stmt.setString(4, "dog");
                stmt.setString(5, ((com.entity.Dog) pet).getDogBreed());
            } else if (pet instanceof com.entity.Cat) {
                stmt.setString(4, "cat");
                stmt.setString(5, ((com.entity.Cat) pet).getCatColor());
            } else {
                stmt.setString(4, "unknown");
                stmt.setString(5, "");
            }

            stmt.executeUpdate();
            System.out.println("Pet added to database successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Pet> getAllPets() {
        List<Pet> petList = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM pets");
            while (rs.next()) {
                String type = rs.getString("pet_type");
                if ("dog".equalsIgnoreCase(type)) {
                    petList.add(new com.entity.Dog(
                        rs.getString("name"), rs.getInt("age"), rs.getString("breed"), rs.getString("extra_info")));
                } else if ("cat".equalsIgnoreCase(type)) {
                    petList.add(new com.entity.Cat(
                        rs.getString("name"), rs.getInt("age"), rs.getString("breed"), rs.getString("extra_info")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return petList;
    }

    @Override
    public void recordCashDonation(String donorName, double amount) {
        try {
            String query = "INSERT INTO donations (donor_name, amount, donation_type, donation_date) VALUES (?, ?, 'cash', CURDATE())";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, donorName);
            stmt.setDouble(2, amount);
            stmt.executeUpdate();
            System.out.println("Donation recorded successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void registerParticipant(String participantName, int eventId) {
        try {
           
            PreparedStatement checkStmt = conn.prepareStatement("SELECT id FROM adoption_events WHERE id = ?");
            checkStmt.setInt(1, eventId);
            ResultSet rs = checkStmt.executeQuery();
            if (!rs.next()) {
                System.out.println("Event ID " + eventId + " does not exist.");
                return;
            }

            String query = "INSERT INTO participants (participant_name, event_id) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, participantName);
            stmt.setInt(2, eventId);
            stmt.executeUpdate();
            System.out.println("Participant registered for event.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void recordItemDonation(String donorName, String itemType, double value) {
        try {
            String query = "INSERT INTO donations (donor_name, amount, donation_type, item_type, donation_date) " +
                           "VALUES (?, ?, 'item', ?, CURDATE())";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, donorName);
            stmt.setDouble(2, value);
            stmt.setString(3, itemType);
            stmt.executeUpdate();
            System.out.println("Item donation recorded successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

