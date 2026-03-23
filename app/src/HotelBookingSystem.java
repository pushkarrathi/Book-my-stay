/**
 MAIN CLASS: HotelBookingApp

 Use Case 10: Booking Cancellation & Inventory Rollback

 Description:
 This class represents the entry point of the
 Hotel Booking Management System.

 The goal is to establish a clear and predictable
 application startup.

 @author Pushkar Rathi
 @version 10.0
 */

import java.util.*;

class RoomInventory {

    private Map<String, Integer> availability;

    public RoomInventory() {
        availability = new HashMap<>();
        availability.put("Single", 1);
        availability.put("Double", 1);
        availability.put("Suite", 1);
    }

    public int getAvailable(String type) {
        return availability.get(type);
    }

    public void increase(String type) {
        availability.put(type, availability.get(type) + 1);
    }

    public void decrease(String type) {
        availability.put(type, availability.get(type) - 1);
    }

    public void display() {
        System.out.println("Inventory: " + availability);
    }
}

class CancellationService {

    // Stack for rollback (LIFO)
    private Stack<String> releasedRooms;

    // reservationId → roomType
    private Map<String, String> reservationMap;

    public CancellationService() {
        releasedRooms = new Stack<>();
        reservationMap = new HashMap<>();
    }

    // Register confirmed booking
    public void registerBooking(String reservationId, String roomType) {
        reservationMap.put(reservationId, roomType);
    }

    // Cancel booking
    public void cancelBooking(String reservationId, RoomInventory inventory) {

        if (!reservationMap.containsKey(reservationId)) {
            System.out.println("Invalid reservation ID");
            return;
        }

        String roomType = reservationMap.get(reservationId);

        // Rollback inventory
        inventory.increase(roomType);

        // Push to stack
        releasedRooms.push(reservationId);

        // Remove booking
        reservationMap.remove(reservationId);

        System.out.println("Booking cancelled successfully for room type: " + roomType);
    }

    // Show rollback history
    public void showRollbackHistory() {
        System.out.println("\nRollback History (LIFO): " + releasedRooms);
    }
}

public class HotelBookingSystem {
    public static void main(String[] args) {

        System.out.println("Booking Cancellation\n");

        RoomInventory inventory = new RoomInventory();
        CancellationService service = new CancellationService();

        // Simulate confirmed booking
        String reservationId = "Single-1";
        service.registerBooking(reservationId, "Single");

        // Decrease inventory (booking done)
        inventory.decrease("Single");

        System.out.println("Before Cancellation:");
        inventory.display();

        // Cancel booking
        service.cancelBooking(reservationId, inventory);

        System.out.println("\nAfter Cancellation:");
        inventory.display();

        // Show rollback
        service.showRollbackHistory();
    }
}