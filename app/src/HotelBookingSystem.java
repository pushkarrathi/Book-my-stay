/**
 MAIN CLASS: HotelBookingApp

 Use Case 2: Basic Room Types & Static Availability

 Description:
 This class represents the entry point of the
 Hotel Booking Management System.

 The goal is to establish a clear and predictable
 application startup.

 @author Pushkar Rathi
 @version 2.0
 */

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

public class HotelBookingSystem {
    public static void main() {
        SingleRoom[] singleRooms = new SingleRoom[5];
        DoubleRoom[] doubleRooms = new DoubleRoom[3];
        SuiteRoom[] suiteRooms = new SuiteRoom[2];

        // Creating instances
        for (int i = 0; i < singleRooms.length; i++) singleRooms[i] = new SingleRoom();
        for (int i = 0; i < doubleRooms.length; i++) doubleRooms[i] = new DoubleRoom();
        for (int i = 0; i < suiteRooms.length; i++) suiteRooms[i] = new SuiteRoom();

        // Example: Displaying details for one of each type
        System.out.println("Single Room Details");
        singleRooms[0].displayRoomDetails();

        System.out.println("\nDouble Room Details");
        doubleRooms[0].displayRoomDetails();

        System.out.println("\nSuite Room Details");
        suiteRooms[0].displayRoomDetails();
    }
}
