import java.util.ArrayList;

public class RideBookingSystem {
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Ride> rides = new ArrayList<>();
    private ArrayList<Booking> bookings = new ArrayList<>();

    private int nextUserId = 1;
    private int nextBookingId = 1;

    public User registerUser(String name, String contact) {
        User user = new User(nextUserId++, name, contact);
        users.add(user);
        return user;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void createRide(String source, String destination, int total_seats, int available_seats, double fare) {
        Ride ride = new Ride(source, destination, total_seats, available_seats, fare);
        rides.add(ride);
    }

    public ArrayList<Ride> getRides() {
        return rides;
    }

    // Bookings
    public Booking bookRide(User user, int rideIndex, int seats) {
        if (rideIndex < 0 || rideIndex >= rides.size()) {
            throw new IllegalArgumentException("Invalid ride selection");
        }
        if (seats <= 0) {
            throw new IllegalArgumentException("Seats must be positive");
        }
        Ride ride = rides.get(rideIndex);
        if (ride.available_seats < seats) {
            throw new IllegalStateException("Not enough seats available");
        }
        ride.available_seats -= seats;
        Booking booking = new Booking(nextBookingId++, user, ride, seats);
        bookings.add(booking);
        return booking;
    }

    public Booking updateBooking(int idx, int seats) {
        if (idx < 0 || idx - 1 >= bookings.size()) {
            throw new IllegalArgumentException("Invalid ride selection");
        }
        if (seats <= 0) {
            throw new IllegalArgumentException("Seats must be positive");
        }
        Ride ride = rides.get(idx - 1);
        if (ride.available_seats < seats) {
            throw new IllegalStateException("Not enough seats available");
        }
        bookings.get(idx - 1).updateSeatsBooked(seats);
        return bookings.get(idx - 1);
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }
}