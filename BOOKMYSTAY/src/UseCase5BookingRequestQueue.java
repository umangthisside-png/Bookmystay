import java.util.LinkedList;
import java.util.Queue;

/**
 * -----------------------------------------------------------
 * MAIN CLASS - UseCase5BookingRequestQueue
 * -----------------------------------------------------------
 *
 * Use Case 5: Booking Request (First-Come-First-Served)
 *
 * Description:
 * Handles booking requests using a queue to ensure fairness.
 *
 * @author Developer
 * @version 5.0
 */

// ---------------- RESERVATION ----------------
class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public void display() {
        System.out.println("Guest : " + guestName + " | Room : " + roomType);
    }
}

// ---------------- QUEUE ----------------
class BookingRequestQueue {

    private Queue<Reservation> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    // Add request
    public void addRequest(Reservation reservation) {
        queue.add(reservation);
        System.out.println("Request added to queue");
    }

    // Display all requests
    public void displayRequests() {

        System.out.println("\n===== Booking Requests Queue =====");

        for (Reservation r : queue) {
            r.display();
        }
    }
}

// ---------------- MAIN ----------------
public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        BookingRequestQueue requestQueue = new BookingRequestQueue();

        // Add requests (FIFO order)
        requestQueue.addRequest(new Reservation("Umang", "Single Room"));
        requestQueue.addRequest(new Reservation("Rahul", "Double Room"));
        requestQueue.addRequest(new Reservation("Amit", "Suite Room"));

        // Display queue
        requestQueue.displayRequests();
    }
}