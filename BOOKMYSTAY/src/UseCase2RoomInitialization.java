/**
 * -----------------------------------------------------------
 * MAIN CLASS - UseCase2RoomInitialization
 * -----------------------------------------------------------
 *
 * Use Case 2: Basic Room Types & Static Availability
 *
 * Description:
 * Demonstrates object-oriented modeling using abstraction,
 * inheritance, and polymorphism.
 *
 * @author Developer
 * @version 2.0
 */

abstract class Room {

    protected String type;
    protected int beds;
    protected double price;

    public Room(String type, int beds, double price) {
        this.type = type;
        this.beds = beds;
        this.price = price;
    }

    public void displayDetails() {
        System.out.println("Room Type : " + type);
        System.out.println("Beds : " + beds);
        System.out.println("Price : " + price);
    }
}

// Single Room
class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 2000);
    }
}

// Double Room
class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 3500);
    }
}

// Suite Room
class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 6000);
    }
}

// Main Class
public class UseCase2RoomInitialization {

    public static void main(String[] args) {

        // Room objects
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Static availability
        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        // Display details
        System.out.println("===== Room Details =====");

        single.displayDetails();
        System.out.println("Available : " + singleAvailable);
        System.out.println();

        doubleRoom.displayDetails();
        System.out.println("Available : " + doubleAvailable);
        System.out.println();

        suite.displayDetails();
        System.out.println("Available : " + suiteAvailable);
    }
}