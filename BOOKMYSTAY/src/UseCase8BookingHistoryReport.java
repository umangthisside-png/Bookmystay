import java.util.*;

/**
 * -----------------------------------------------------------
 * MAIN CLASS - UseCase8BookingHistoryReport
 * -----------------------------------------------------------
 *
 * Use Case 8: Booking History & Reporting
 */

// ---------------- RESERVATION ----------------
class ReservationRecord {

    private String guestName;
    private String roomType;
    private String roomId;

    public ReservationRecord(String guestName, String roomType, String roomId) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomId = roomId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getRoomId() {
        return roomId;
    }
}

// ---------------- HISTORY ----------------
class BookingHistory {

    private List<ReservationRecord> history = new ArrayList<>();

    // Add booking
    public void addRecord(ReservationRecord record) {
        history.add(record);
    }

    // Get all bookings
    public List<ReservationRecord> getAllRecords() {
        return history;
    }
}

// ---------------- REPORT SERVICE ----------------
class BookingReportService {

    public void displayAllBookings(List<ReservationRecord> records) {

        System.out.println("===== Booking History =====");

        for (ReservationRecord r : records) {
            System.out.println(
                    "Guest: " + r.getGuestName() +
                            " | Room: " + r.getRoomType() +
                            " | Room ID: " + r.getRoomId()
            );
        }
    }

    // Simple summary report
    public void generateSummary(List<ReservationRecord> records) {

        Map<String, Integer> countMap = new HashMap<>();

        for (ReservationRecord r : records) {
            countMap.put(
                    r.getRoomType(),
                    countMap.getOrDefault(r.getRoomType(), 0) + 1
            );
        }

        System.out.println("\n===== Booking Summary =====");

        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            System.out.println(entry.getKey() + " booked: " + entry.getValue());
        }
    }
}

// ---------------- MAIN ----------------
public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();

        // Simulate confirmed bookings
        history.addRecord(new ReservationRecord("Umang", "Single Room", "SI101"));
        history.addRecord(new ReservationRecord("Rahul", "Double Room", "DO102"));
        history.addRecord(new ReservationRecord("Amit", "Single Room", "SI103"));
        history.addRecord(new ReservationRecord("Ravi", "Suite Room", "SU104"));

        BookingReportService reportService = new BookingReportService();

        // Display all bookings
        reportService.displayAllBookings(history.getAllRecords());

        // Generate summary
        reportService.generateSummary(history.getAllRecords());
    }
}