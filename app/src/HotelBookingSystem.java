/**
 MAIN CLASS: HotelBookingApp

 Use Case 9: Error Handling & Validation

 Description:
 This class represents the entry point of the
 Hotel Booking Management System.

 The goal is to establish a clear and predictable
 application startup.

 @author Pushkar Rathi
 @version 9.0
 */

import java.util.*;

class InvalidBookingException extends Exception {

    public InvalidBookingException(String message) {
        super(message);
    }
}

class ReservationValidator {

    public void validate(String guestName, String roomType)
            throws InvalidBookingException {

        // Validate name
        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty");
        }

        // Validate room type
        if (!(roomType.equalsIgnoreCase("Single") ||
                roomType.equalsIgnoreCase("Double") ||
                roomType.equalsIgnoreCase("Suite"))) {

            throw new InvalidBookingException("Invalid room type selected");
        }
    }
}

public class HotelBookingSystem {
    public static void main(String[] args) {

        System.out.println("Booking Validation\n");

        Scanner scanner = new Scanner(System.in);

        try {
            // Input
            System.out.print("Enter guest name: ");
            String name = scanner.nextLine();

            System.out.print("Enter room type (Single/Double/Suite): ");
            String room = scanner.nextLine();

            // Validation
            ReservationValidator validator = new ReservationValidator();
            validator.validate(name, room);

            // Success
            System.out.println("Booking successful!");

        } catch (InvalidBookingException e) {
            // Error handling
            System.out.println("Booking failed: " + e.getMessage());

        } finally {
            scanner.close();
        }
    }
}