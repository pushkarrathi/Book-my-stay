/**
 MAIN CLASS: HotelBookingApp

 Use Case 7: Add-On Service Selection

 Description:
 This class represents the entry point of the
 Hotel Booking Management System.

 The goal is to establish a clear and predictable
 application startup.

 @author Pushkar Rathi
 @version 7.0
 */

import java.util.*;

class Service {

    private String serviceName;
    private double cost;

    public Service(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getCost() {
        return cost;
    }
}

class AddOnServiceManager {

    // Map: ReservationID → List of services
    private Map<String, List<Service>> servicesMap;

    public AddOnServiceManager() {
        servicesMap = new HashMap<>();
    }

    // Add service to reservation
    public void addService(String reservationId, Service service) {

        servicesMap.putIfAbsent(reservationId, new ArrayList<>());
        servicesMap.get(reservationId).add(service);
    }

    // Calculate total cost
    public double calculateTotalServiceCost(String reservationId) {

        double total = 0;

        List<Service> list = servicesMap.get(reservationId);

        if (list != null) {
            for (Service s : list) {
                total += s.getCost();
            }
        }

        return total;
    }
}

public class HotelBookingSystem {
    public static void main(String[] args) {

        System.out.println("Add-On Service Selection\n");

        AddOnServiceManager manager = new AddOnServiceManager();

        String reservationId = "Single-1";

        // Create services
        Service breakfast = new Service("Breakfast", 500);
        Service wifi = new Service("WiFi", 200);
        Service pickup = new Service("Airport Pickup", 800);

        // Add services
        manager.addService(reservationId, breakfast);
        manager.addService(reservationId, wifi);
        manager.addService(reservationId, pickup);

        // Calculate total
        double totalCost = manager.calculateTotalServiceCost(reservationId);

        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Total Add-On Cost: " + totalCost);
    }
}