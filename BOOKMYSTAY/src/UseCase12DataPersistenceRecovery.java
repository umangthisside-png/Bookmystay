import java.io.*;
import java.util.*;

/**
 * -----------------------------------------------------------
 * MAIN CLASS - UseCase12DataPersistenceRecovery
 * -----------------------------------------------------------
 *
 * Use Case 12: Data Persistence & System Recovery
 */

// ---------------- STATE CLASS ----------------
class SystemState implements Serializable {

    Map<String, Integer> inventory;
    List<String> bookings;

    public SystemState(Map<String, Integer> inventory, List<String> bookings) {
        this.inventory = inventory;
        this.bookings = bookings;
    }
}

// ---------------- PERSISTENCE SERVICE ----------------
class PersistenceService {

    private static final String FILE_NAME = "system_state.dat";

    // Save state
    public void save(SystemState state) {

        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            out.writeObject(state);
            System.out.println("State SAVED to file.");

        } catch (IOException e) {
            System.out.println("Error saving state.");
        }
    }

    // Load state
    public SystemState load() {

        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            System.out.println("State LOADED from file.");
            return (SystemState) in.readObject();

        } catch (Exception e) {

            System.out.println("No previous data found. Starting fresh.");
            return null;
        }
    }
}

// ---------------- MAIN ----------------
public class UseCase12DataPersistenceRecovery {

    public static void main(String[] args) {

        PersistenceService service = new PersistenceService();

        // Try loading previous state
        SystemState state = service.load();

        Map<String, Integer> inventory;
        List<String> bookings;

        if (state != null) {

            // Restore data
            inventory = state.inventory;
            bookings = state.bookings;

            System.out.println("\nRecovered Data:");
        } else {

            // Fresh start
            inventory = new HashMap<>();
            inventory.put("Single Room", 2);
            inventory.put("Double Room", 1);

            bookings = new ArrayList<>();

            System.out.println("\nNew System Started:");
        }

        // Simulate booking
        bookings.add("RES101 - Single Room");
        inventory.put("Single Room", inventory.get("Single Room") - 1);

        // Display state
        System.out.println("\nCurrent Bookings:");
        for (String b : bookings) {
            System.out.println(b);
        }

        System.out.println("\nCurrent Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        // Save state before exit
        service.save(new SystemState(inventory, bookings));
    }
}