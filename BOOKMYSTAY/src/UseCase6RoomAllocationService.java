import java.util.*;
import java.lang.reflect.Field;

/**
 * -----------------------------------------------------------
 * MAIN CLASS - UseCase6RoomAllocationService
 * -----------------------------------------------------------
 */

// ---------------- BOOKING SERVICE ----------------
class BookingService {

    private RoomInventory inventory;

    private Set<String> allocatedRoomIds = new HashSet<>();
    private Map<String, Set<String>> roomAllocations = new HashMap<>();

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void processRequests(Queue<Reservation> queue) {

        System.out.println("===== Processing Bookings =====");

        while (!queue.isEmpty()) {

            Reservation r = queue.poll();

            String roomType = getRoomType(r);
            String guestName = getGuestName(r);

            int available = inventory.getAvailability(roomType);

            if (available > 0) {

                String roomId = generateRoomId(roomType);

                allocatedRoomIds.add(roomId);

                roomAllocations
                        .computeIfAbsent(roomType, k -> new HashSet<>())
                        .add(roomId);

                inventory.updateAvailability(roomType, available - 1);

                System.out.println("Booking CONFIRMED for " + guestName +
                        " | Room: " + roomType +
                        " | Room ID: " + roomId);

            } else {

                System.out.println("Booking FAILED for " + guestName +
                        " | No rooms available for " + roomType);
            }
        }
    }

    // 🔥 ACCESS PRIVATE FIELD USING REFLECTION
    private String getRoomType(Reservation r) {
        try {
            Field field = r.getClass().getDeclaredField("roomType");
            field.setAccessible(true);
            return (String) field.get(r);
        } catch (Exception e) {
            return "Unknown";
        }
    }

    private String getGuestName(Reservation r) {
        try {
            Field field = r.getClass().getDeclaredField("guestName");
            field.setAccessible(true);
            return (String) field.get(r);
        } catch (Exception e) {
            return "Unknown";
        }
    }

    private String generateRoomId(String type) {

        String prefix = type.replace(" ", "").substring(0, 2).toUpperCase();

        String id;

        do {
            id = prefix + (int)(Math.random() * 1000);
        } while (allocatedRoomIds.contains(id));

        return id;
    }
}

// ---------------- MAIN ----------------
public class UseCase6RoomAllocationService {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        Queue<Reservation> queue = new LinkedList<>();

        queue.add(new Reservation("Umang", "Single Room"));
        queue.add(new Reservation("Rahul", "Single Room"));
        queue.add(new Reservation("Amit", "Single Room"));
        queue.add(new Reservation("Ravi", "Suite Room"));

        BookingService service = new BookingService(inventory);
        service.processRequests(queue);
    }
}