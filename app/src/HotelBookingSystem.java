/**
 MAIN CLASS: HotelBookingApp

 Use Case 4: Room Search & Availability Check

 Description:
 This class represents the entry point of the
 Hotel Booking Management System.

 The goal is to establish a clear and predictable
 application startup.

 @author Pushkar Rathi
 @version 4.0
 */

import java.util.HashMap;
import java.util.Map;

abstract class Room {
    protected int numberOfBeds;
    protected int squareFeet;
    protected double pricePerNight;

    public Room(int numberOfBeds, int squareFeet, double pricePerNight) {
        this.numberOfBeds = numberOfBeds;
        this.squareFeet = squareFeet;
        this.pricePerNight = pricePerNight;
    }

    public void displayRoomDetails() {
        System.out.println("Beds: " + numberOfBeds);
        System.out.println("Size: " + squareFeet + " sqft");
        System.out.println("Price per night: " + pricePerNight);
    }
}

class SingleRoom extends Room {
    public SingleRoom() { super(1, 250, 1500.0); }
}

class DoubleRoom extends Room {
    public DoubleRoom() { super(2, 400, 2500.0); }
}

class SuiteRoom extends Room {
    public SuiteRoom() { super(3, 750, 5000.0); }
}

class RoomInventory {
    private Map<String, Integer> roomAvailability;

    public RoomInventory() {
        roomAvailability = new HashMap<>();
        initializeInventory();
    }

    private void initializeInventory() {
        roomAvailability.put("Single Room", 5);
        roomAvailability.put("Double Room", 3);
        roomAvailability.put("Suite Room", 2);
    }

    public Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }

    public void updateAvailability(String roomType, int count) {
        roomAvailability.put(roomType, count);
    }
}

class RoomSearchService {
    public void searchAvailableRooms(RoomInventory inventory, Room single, Room doubleRoom, Room suite) {
        Map<String, Integer> availability = inventory.getRoomAvailability();

        System.out.println("\nRoom Search Results");

        // Single Room Check
        if (availability.getOrDefault("Single Room", 0) > 0) {
            System.out.println("\nSingle Room:");
            single.displayRoomDetails();
            System.out.println("Available: " + availability.get("Single Room"));
        }

        // Double Room Check
        if (availability.getOrDefault("Double Room", 0) > 0) {
            System.out.println("\nDouble Room:");
            doubleRoom.displayRoomDetails();
            System.out.println("Available: " + availability.get("Double Room"));
        }

        // Suite Room Check
        if (availability.getOrDefault("Suite Room", 0) > 0) {
            System.out.println("\nSuite Room:");
            suite.displayRoomDetails();
            System.out.println("Available: " + availability.get("Suite Room"));
        }
    }
}


public class HotelBookingSystem {
    public static void main(String[] args) {
        // Initialize Components
        RoomInventory inventory = new RoomInventory();
        RoomSearchService searchService = new RoomSearchService();

        // Create Room Definitions
        Room single = new SingleRoom();
        Room doubleRm = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Perform Search (Read-only access)
        searchService.searchAvailableRooms(inventory, single, doubleRm, suite);
    }
}