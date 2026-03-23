/**
 MAIN CLASS: HotelBookingApp

 Use Case 6: Reservation Confirmation & Room Allocation

 Description:
 This class represents the entry point of the
 Hotel Booking Management System.

 The goal is to establish a clear and predictable
 application startup.

 @author Pushkar Rathi
 @version 6.0
 */

import java.util.*;

class Reservation {
    String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class BookingRequestQueue {
    private Queue<Reservation> queue = new LinkedList<>();

    public void addRequest(Reservation r) {
        queue.offer(r);
    }

    public Reservation getNext() {
        return queue.poll();
    }

    public boolean hasRequests() {
        return !queue.isEmpty();
    }
}

class RoomInventory {

    private Map<String, Integer> availability = new HashMap<>();

    public RoomInventory() {
        availability.put("Single", 2);
        availability.put("Double", 2);
        availability.put("Suite", 1);
    }

    public boolean isAvailable(String type) {
        return availability.get(type) > 0;
    }

    public void reduceRoom(String type) {
        availability.put(type, availability.get(type) - 1);
    }
}

class RoomAllocationService {

    // Track assigned room IDs
    private Set<String> allocatedRooms = new HashSet<>();

    // Map type → assigned room IDs
    private Map<String, Set<String>> assignedRooms = new HashMap<>();

    public RoomAllocationService() {
        assignedRooms.put("Single", new HashSet<>());
        assignedRooms.put("Double", new HashSet<>());
        assignedRooms.put("Suite", new HashSet<>());
    }

    public void allocateRoom(Reservation r, RoomInventory inventory) {

        if (!inventory.isAvailable(r.roomType)) {
            System.out.println("No rooms available for " + r.roomType);
            return;
        }

        String roomId = generateRoomId(r.roomType);

        allocatedRooms.add(roomId);
        assignedRooms.get(r.roomType).add(roomId);

        inventory.reduceRoom(r.roomType);

        System.out.println("Booking confirmed for Guest: "
                + r.guestName
                + ", Room ID: "
                + roomId);
    }

    private String generateRoomId(String type) {
        int count = assignedRooms.get(type).size() + 1;
        return type + "-" + count;
    }
}

public class HotelBookingSystem {
    public static void main(String[] args) {

        BookingRequestQueue queue = new BookingRequestQueue();
        RoomInventory inventory = new RoomInventory();
        RoomAllocationService service = new RoomAllocationService();

        // Add booking requests (FIFO)
        queue.addRequest(new Reservation("Asha", "Single"));
        queue.addRequest(new Reservation("Rahul", "Single"));
        queue.addRequest(new Reservation("Meena", "Double"));
        queue.addRequest(new Reservation("Kiran", "Suite"));

        System.out.println("Room Allocation Processing\n");

        while (queue.hasRequests()) {
            Reservation r = queue.getNext();
            service.allocateRoom(r, inventory);
        }
    }
}