import java.util.HashMap;
import java.util.Map;

/**
 * -----------------------------------------------------------
 * MAIN CLASS - UseCase3InventorySetup
 * -----------------------------------------------------------
 *
 * Use Case 3: Centralized Room Inventory Management
 *
 * Description:
 * Demonstrates centralized inventory using HashMap.
 *
 * @author Developer
 * @version 3.0
 */

class RoomInventory {

    private Map<String, Integer> inventory;

    // Constructor initializes inventory
    public RoomInventory() {
        inventory = new HashMap<>();

        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    // Get availability
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Update availability
    public void updateAvailability(String roomType, int newCount) {
        inventory.put(roomType, newCount);
    }

    // Display full inventory
    public void displayInventory() {
        System.out.println("===== Room Inventory =====");

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

public class UseCase3InventorySetup {

    public static void main(String[] args) {

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Display inventory
        inventory.displayInventory();

        // Example update
        inventory.updateAvailability("Single Room", 4);

        System.out.println("\nAfter Update:");
        inventory.displayInventory();
    }
}