/**
 MAIN CLASS: HotelBookingApp

 Use Case 3: Centralized Room Inventory Management

 Description:
 This class represents the entry point of the
 Hotel Booking Management System.

 The goal is to establish a clear and predictable
 application startup.

 @author Pushkar Rathi
 @version 3.0
 */

import java.util.HashMap;
import java.util.Map;

/**
 * ABSTRACT CLASS - Room
 * Models attributes intrinsic to a room type.
 */
abstract class Room {
    protected int numberOfBeds;
    protected int squareFeet;
    protected double pricePerNight;

    /**
     * Constructor used by child classes to initialize common attributes.
     */
    public Room(int numberOfBeds, int squareFeet, double pricePerNight) {
        this.numberOfBeds = numberOfBeds;
        this.squareFeet = squareFeet;
        this.pricePerNight = pricePerNight;
    }

    /**
     * Displays room details.
     */
    public void displayRoomDetails() {
        System.out.println("Beds: " + numberOfBeds);
        System.out.println("Size: " + squareFeet + " sqft");
        System.out.println("Price per night: " + pricePerNight);
    }
}

// Single Room Implementation
 class SingleRoom extends Room {
    public SingleRoom() {
        super(1, 250, 1500.0);
    }
}

// Double Room Implementation
 class DoubleRoom extends Room {
    public DoubleRoom() {
        super(2, 400, 2500.0);
    }
}

// Suite Room Implementation
 class SuiteRoom extends Room {
    public SuiteRoom() {
        super(3, 750, 5000.0);
    }
}

class RoomInventory {
    // Maps Room Type Name -> Available Count
    private Map<String, Integer> roomAvailability;

    public RoomInventory() {
        roomAvailability = new HashMap<>();
        initializeInventory();
    }

    /**
     * Centralizes inventory setup with default values from your data.
     */
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

public class HotelBookingSystem {
    public static void main(String[] args) {
        // 1. Initialize the Centralized Inventory
        RoomInventory inventory = new RoomInventory();

        // 2. Initialize one instance of each room type to get their characteristics
        Room single = new SingleRoom();
        Room doubleRm = new DoubleRoom();
        Room suite = new SuiteRoom();

        System.out.println("--- Hotel Room Inventory Status ---");

        // 3. Combine Room data with Inventory data for display
        displayStatus("Single Room", single, inventory);
        displayStatus("Double Room", doubleRm, inventory);
        displayStatus("Suite Room", suite, inventory);
    }

    private static void displayStatus(String typeName, Room room, RoomInventory inv) {
        int available = inv.getRoomAvailability().getOrDefault(typeName, 0);

        System.out.println("\n" + typeName + ":");
        room.displayRoomDetails(); // Shows Beds, Size, and Price
        System.out.println("Available Rooms: " + available);
    }
}
