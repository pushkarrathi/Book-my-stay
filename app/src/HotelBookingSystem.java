/**
 MAIN CLASS: HotelBookingApp

 Use Case 8: Booking History & Reporting

 Description:
 This class represents the entry point of the
 Hotel Booking Management System.

 The goal is to establish a clear and predictable
 application startup.

 @author Pushkar Rathi
 @version 8.0
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

class BookingHistory {

    private List<Reservation> confirmedReservations;

    public BookingHistory() {
        confirmedReservations = new ArrayList<>();
    }

    // Add confirmed reservation
    public void addReservation(Reservation reservation) {
        confirmedReservations.add(reservation);
    }

    // Get all reservations
    public List<Reservation> getAllReservations() {
        return confirmedReservations;
    }
}

class BookingReportService {

    // Generate report
    public void generateReport(BookingHistory history) {

        System.out.println("\nBooking History Report\n");

        for (Reservation r : history.getAllReservations()) {
            System.out.println("Guest: " + r.guestName +
                    ", Room Type: " + r.roomType);
        }
    }
}

public class HotelBookingSystem {
    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();

        // Add confirmed bookings
        history.addReservation(new Reservation("Adhi", "Single"));
        history.addReservation(new Reservation("Soumya", "Double"));
        history.addReservation(new Reservation("Vanshith", "Suite"));

        // Generate report
        BookingReportService report = new BookingReportService();
        report.generateReport(history);
    }
}