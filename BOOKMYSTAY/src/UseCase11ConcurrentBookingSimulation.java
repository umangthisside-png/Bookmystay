import java.util.*;

/**
 * -----------------------------------------------------------
 * MAIN CLASS - UseCase11ConcurrentBookingSimulation
 * -----------------------------------------------------------
 *
 * Use Case 11: Concurrent Booking Simulation (Thread Safety)
 */

// ---------------- INVENTORY ----------------
class ConcurrentInventory {

    private Map<String, Integer> inventory = new HashMap<>();

    public ConcurrentInventory() {
        inventory.put("Single Room", 2);
    }

    // 🔥 SYNCHRONIZED METHOD
    public synchronized boolean bookRoom(String roomType) {

        int available = inventory.getOrDefault(roomType, 0);

        if (available > 0) {
            inventory.put(roomType, available - 1);

            System.out.println(Thread.currentThread().getName()
                    + " booked " + roomType +
                    " | Remaining: " + (available - 1));

            return true;
        } else {
            System.out.println(Thread.currentThread().getName()
                    + " FAILED to book " + roomType);

            return false;
        }
    }
}

// ---------------- TASK ----------------
class BookingTask implements Runnable {

    private ConcurrentInventory inventory;
    private String roomType;

    public BookingTask(ConcurrentInventory inventory, String roomType) {
        this.inventory = inventory;
        this.roomType = roomType;
    }

    @Override
    public void run() {
        inventory.bookRoom(roomType);
    }
}

// ---------------- MAIN ----------------
public class UseCase11ConcurrentBookingSimulation {

    public static void main(String[] args) {

        ConcurrentInventory inventory = new ConcurrentInventory();

        // Simulate multiple users
        Thread t1 = new Thread(new BookingTask(inventory, "Single Room"), "User-1");
        Thread t2 = new Thread(new BookingTask(inventory, "Single Room"), "User-2");
        Thread t3 = new Thread(new BookingTask(inventory, "Single Room"), "User-3");

        // Start threads
        t1.start();
        t2.start();
        t3.start();
    }
}