package com.main;

import com.entity.*;
import com.dao.*;
import java.util.*;
import com.exception.*;

public class Main {
    public static void main(String[] args) throws InsufficientFundsException {
        Scanner sc = new Scanner(System.in);
        IPetService service = new PetServiceImpl();

        while (true) {
            System.out.println("\n--Petpals -The Pet Adoption Platform--1\n1. Add Pet\n2. List Pets\n3. Donation\n4. Register for Event\n5. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter pet type (dog/cat): ");
                        String type = sc.nextLine();
                        System.out.print("Name: "); String name = sc.nextLine();
                        System.out.print("Age: "); int age = sc.nextInt(); sc.nextLine();
                        if (age <= 0) throw new InvalidPetAgeException("Age must be positive");
                        System.out.print("Breed: "); String breed = sc.nextLine();

                        if (type.equalsIgnoreCase("dog")) {
                            System.out.print("Dog Breed: ");
                            String dogBreed = sc.nextLine();
                            service.addPet(new Dog(name, age, breed, dogBreed));
                        } else if (type.equalsIgnoreCase("cat")) {
                            System.out.print("Cat Color: ");
                            String catColor = sc.nextLine();
                            service.addPet(new Cat(name, age, breed, catColor));
                        } else {
                            System.out.println("Invalid type.");
                        }
                    } catch (InvalidPetAgeException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 2:
                    List<Pet> pets = service.getAllPets();
                    pets.forEach(System.out::println);
                    break;
               
                case 3:
                    System.out.print("Donation type (cash/item): ");
                    String dtype = sc.nextLine();
                    if (dtype.equalsIgnoreCase("cash")) {
                        System.out.print("Donor name: ");
                        String donor = sc.nextLine();
                        System.out.print("Amount: ");
                        double amt = sc.nextDouble(); sc.nextLine();
                        if (amt < 10) throw new InsufficientFundsException("Minimum donation is Rs.500");
                        service.recordCashDonation(donor, amt);
                    } else if (dtype.equalsIgnoreCase("item")) {
                        System.out.print("Donor name: ");
                        String donor = sc.nextLine();
                        System.out.print("Item type: ");
                        String item = sc.nextLine();
                        System.out.print("Estimated value: ");
                        double value = sc.nextDouble(); sc.nextLine();
                        service.recordItemDonation(donor, item, value);
                    } else {
                        System.out.println("Invalid donation type.");
                    }
                    break;

                case 4:
                    System.out.print("Enter your name: ");
                    String participant = sc.nextLine();
                    System.out.print("Event ID: ");
                    int eventId = sc.nextInt();
                    sc.nextLine();
                    service.registerParticipant(participant, eventId);
                    break;
                case 5:
                	    System.out.println("Thank you for using petpals");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
