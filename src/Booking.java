import java.util.ArrayList;

public class Booking {
    private int bookingId;
    private ArrayList<User> Users;
    private Ride ride;
    private int seatsBooked;

    public Booking(int bookingId, User user, Ride ride, int seatsBooked) {
        this.bookingId = bookingId;
        Users = new ArrayList<>();
        this.Users.add(user);
        this.ride = ride;
        this.seatsBooked = seatsBooked;
    }

    public int getBookingId() {
        return bookingId;
    }

    public ArrayList<User> getUser() {
        return Users;
    }

    public Ride getRide() {
        return ride;
    }

    public int getSeatsBooked() {
        return seatsBooked;
    }

    public void updateSeatsBooked(int newseats) {
        seatsBooked += newseats;
        ride.available_seats -= newseats;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", users=" + Users +
                ", ride=" + ride +
                ", seatsBooked=" + seatsBooked +
                '}';
    }
}