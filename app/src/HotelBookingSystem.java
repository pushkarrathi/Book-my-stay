/**
 MAIN CLASS: HotelBookingApp

 Use Case 5: Booking Request (First-Come-First-Served)

 Description:
 This class represents the entry point of the
 Hotel Booking Management System.

 The goal is to establish a clear and predictable
 application startup.

 @author Pushkar Rathi
 @version 5.0
 */

import java.util.LinkedList;
import java.util.Queue;

class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

class BookingRequestQueue {

    private Queue<Reservation> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    // Add booking request
    public void addRequest(Reservation reservation) {
        queue.offer(reservation);
    }

    // Process next request
    public Reservation processRequest() {
        return queue.poll();
    }

    // Check if queue has requests
    public boolean hasRequests() {
        return !queue.isEmpty();
    }
}

public class HotelBookingSystem {
    public static void main(String[] args) {

        System.out.println("Booking Request Queue (FIFO)\n");

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Create reservations
        Reservation r1 = new Reservation("John", "Single");
        Reservation r2 = new Reservation("Alice", "Double");
        Reservation r3 = new Reservation("Kumar", "Suite");

        // Add to queue
        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);

        // Process requests FIFO
        while (bookingQueue.hasRequests()) {
            Reservation r = bookingQueue.processRequest();

            System.out.println("Processing booking for Guest: "
                    + r.getGuestName()
                    + ", Room Type: "
                    + r.getRoomType());
        }
    }
}