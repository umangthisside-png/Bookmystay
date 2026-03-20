/**
 * -----------------------------------------------------------
 * MAIN CLASS - UseCase4RoomSearch
 * -----------------------------------------------------------
 *
 * Use Case 4: Room Search & Availability Check
 */

// ---------------- SEARCH SERVICE ----------------
class RoomSearchService {

    private RoomInventory inventory;

    public RoomSearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void searchAvailableRooms(Room[] rooms) {

        System.out.println("===== Available Rooms =====");

        for (Room room : rooms) {

            int available = 0;

            // 🔥 Match using instanceof (NO getType needed)
            if (room instanceof SingleRoom) {
                available = inventory.getAvailability("Single Room");
            } else if (room instanceof DoubleRoom) {
                available = inventory.getAvailability("Double Room");
            } else if (room instanceof SuiteRoom) {
                available = inventory.getAvailability("Suite Room");
            }

            if (available > 0) {
                room.displayDetails();
                System.out.println("Available : " + available);
                System.out.println();
            }
        }
    }
}

// ---------------- MAIN ----------------
public class UseCase4RoomSearch {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        Room[] rooms = {
                new SingleRoom(),
                new DoubleRoom(),
                new SuiteRoom()
        };

        RoomSearchService searchService = new RoomSearchService(inventory);

        searchService.searchAvailableRooms(rooms);
    }
}