import java.util.*;

/**
 * -----------------------------------------------------------
 * MAIN CLASS - UseCase10BookingCancellation
 * -----------------------------------------------------------
 *
 * Use Case 10: Booking Cancellation & Inventory Rollback
 */

// ---------------- INVENTORY ----------------
class CancelInventory {

    private Map<String, Integer> inventory = new HashMap<>();

    public CancelInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    public void increaseAvailability(String type) {
        inventory.put(type, inventory.getOrDefault(type, 0) + 1);
    }

    public void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

// ---------------- CANCELLATION SERVICE ----------------
class CancellationService {

    // Stack to track cancelled room IDs (LIFO)
    private Stack<String> rollbackStack = new Stack<>();

    // Store reservation data
    private Map<String, String> reservationMap = new HashMap<>();

    private CancelInventory inventory;

    public CancellationService(CancelInventory inventory) {
        this.inventory = inventory;
    }

    // Add confirmed booking (simulate)
    public void addBooking(String reservationId, String roomType, String roomId) {
        reservationMap.put(reservationId, roomType);
        System.out.println("Booking stored: " + reservationId + " → " + roomType + " (" + roomId + ")");
    }

    // Cancel booking
    public void cancelBooking(String reservationId) {

        if (!reservationMap.containsKey(reservationId)) {
            System.out.println("ERROR: Reservation not found: " + reservationId);
            return;
        }

        String roomType = reservationMap.get(reservationId);

        // Push into rollback stack
        rollbackStack.push(reservationId);

        // Restore inventory
        inventory.increaseAvailability(roomType);

        // Remove booking
        reservationMap.remove(reservationId);

        System.out.println("Booking CANCELLED: " + reservationId);
    }

    // Display rollback stack
    public void displayRollbackStack() {
        System.out.println("\nRollback Stack (Recent Cancellations): " + rollbackStack);
    }
}

// ---------------- MAIN ----------------
public class UseCase10BookingCancellation {

    public static void main(String[] args) {

        CancelInventory inventory = new CancelInventory();
        CancellationService service = new CancellationService(inventory);

        // Simulate confirmed bookings
        service.addBooking("RES101", "Single Room", "SI101");
        service.addBooking("RES102", "Double Room", "DO102");

        // Cancel booking
        service.cancelBooking("RES102"); // latest booking
        service.cancelBooking("RES101");

        // Invalid cancellation
        service.cancelBooking("RES999");

        // Display rollback + inventory
        service.displayRollbackStack();
        inventory.displayInventory();
    }
}