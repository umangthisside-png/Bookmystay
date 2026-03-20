import java.util.*;

/**
 * -----------------------------------------------------------
 * MAIN CLASS - UseCase7AddOnServiceSelection
 * -----------------------------------------------------------
 *
 * Use Case 7: Add-On Service Selection
 */

// ---------------- SERVICE ----------------
class AddOnService {

    private String name;
    private double price;

    public AddOnService(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}

// ---------------- SERVICE MANAGER ----------------
class AddOnServiceManager {

    // Reservation ID → List of Services
    private Map<String, List<AddOnService>> serviceMap = new HashMap<>();

    // Add service to reservation
    public void addService(String reservationId, AddOnService service) {

        serviceMap
                .computeIfAbsent(reservationId, k -> new ArrayList<>())
                .add(service);

        System.out.println(service.getName() + " added to " + reservationId);
    }

    // Calculate total cost
    public double calculateTotal(String reservationId) {

        double total = 0;

        List<AddOnService> services = serviceMap.get(reservationId);

        if (services != null) {
            for (AddOnService s : services) {
                total += s.getPrice();
            }
        }

        return total;
    }

    // Display services
    public void displayServices(String reservationId) {

        System.out.println("\nServices for Reservation: " + reservationId);

        List<AddOnService> services = serviceMap.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No services selected.");
            return;
        }

        for (AddOnService s : services) {
            System.out.println("- " + s.getName() + " : " + s.getPrice());
        }

        System.out.println("Total Add-On Cost: " + calculateTotal(reservationId));
    }
}

// ---------------- MAIN ----------------
public class UseCase7AddOnServiceSelection {

    public static void main(String[] args) {

        AddOnServiceManager manager = new AddOnServiceManager();

        String reservationId = "RES101";

        // Add services
        manager.addService(reservationId, new AddOnService("Breakfast", 500));
        manager.addService(reservationId, new AddOnService("WiFi", 200));
        manager.addService(reservationId, new AddOnService("Spa", 1000));

        // Display
        manager.displayServices(reservationId);
    }
}