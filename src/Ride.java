public class Ride {
    String Source;
    String Destination;
    int total_seats;
    int available_seats;
    double fare;

    public Ride(String source, String destination, int total_seats, int available_seats, double fare) {
        Source = source;
        Destination = destination;
        this.total_seats = total_seats;
        this.available_seats = available_seats;
        this.fare = fare;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "Source='" + Source + '\'' +
                ", Destination='" + Destination + '\'' +
                ", total_seats=" + total_seats +
                ", available_seats=" + available_seats +
                ", fare=" + fare +
                '}';
    }
}