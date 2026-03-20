import java.util.*;

/**
 * -----------------------------------------------------------
 * MAIN CLASS - UseCase9ErrorHandlingValidation
 * -----------------------------------------------------------
 *
 * Use Case 9: Error Handling & Validation
 */

// ---------------- CUSTOM EXCEPTION ----------------
class InvalidBookingException extends Exception {

    public InvalidBookingException(String message) {
        super(message);
    }
}

// ---------------- VALIDATOR ----------------
class BookingValidator {

    private static final Set<String> validRoomTypes = new HashSet<>(
            Arrays.asList("Single Room", "Double Room", "Suite Room")
    );

    public static void validate(String roomType, int availability)
            throws InvalidBookingException {

        // Validate room type
        if (!validRoomTypes.contains(roomType)) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }

        // Validate availability
        if (availability <= 0) {
            throw new InvalidBookingException(
                    "No rooms available for: " + roomType
            );
        }
    }
}

// ---------------- INVENTORY ----------------
class SimpleInventory {

    private Map<String, Integer> inventory = new HashMap<>();

    public SimpleInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 0); // invalid case
    }

    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }
}

// ---------------- MAIN ----------------
public class UseCase9ErrorHandlingValidation {

    public static void main(String[] args) {

        SimpleInventory inventory = new SimpleInventory();

        String[] testRequests = {
                "Single Room",
                "Suite Room",   // no availability
                "Deluxe Room"   // invalid type
        };

        for (String roomType : testRequests) {

            try {

                int available = inventory.getAvailability(roomType);

                BookingValidator.validate(roomType, available);

                System.out.println("Booking VALID for: " + roomType);

            } catch (InvalidBookingException e) {

                System.out.println("ERROR: " + e.getMessage());
            }
        }

        System.out.println("\nSystem continues running safely...");
    }
}