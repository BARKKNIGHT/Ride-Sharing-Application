import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RideBookingSystem system = new RideBookingSystem();
        seedSampleData(system);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Carpooling CLI");
        while (true) {
            printMenu();
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();
            try {
                switch (choice) {
                    case "1":
                        registerUser(scanner, system);
                        break;
                    case "2":
                        listUsers(system);
                        break;
                    case "3":
                        createRide(scanner, system);
                        break;
                    case "4":
                        listRides(system);
                        break;
                    case "5":
                        bookRide(scanner, system);
                        break;
                    case "6":
                        listBookings(system);
                        break;
                    case "7":
                        UpdateSeatsBooked(scanner, system);
                        break;
                    case "0":
                        System.out.println("Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("------------------------------");
        System.out.println("1. Register user");
        System.out.println("2. List users");
        System.out.println("3. Create ride");
        System.out.println("4. List rides");
        System.out.println("5. Book a ride");
        System.out.println("6. View bookings");
        System.out.println("7. Update seats booked");
        System.out.println("0. Exit");
    }

    private static void registerUser(Scanner scanner, RideBookingSystem system) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter contact (phone/email): ");
        String contact = scanner.nextLine().trim();
        User user = system.registerUser(name, contact);
        System.out.println("Registered: " + user);
    }

    private static void listUsers(RideBookingSystem system) {
        ArrayList<User> users = system.getUsers();
        if (users.isEmpty()) {
            System.out.println("No users registered yet.");
            return;
        }
        System.out.println("Users:");
        for (User u : users) {
            System.out.println("- " + u);
        }
    }

    private static void createRide(Scanner scanner, RideBookingSystem system) {
        System.out.print("Source: ");
        String src = scanner.nextLine().trim();
        System.out.print("Destination: ");
        String dst = scanner.nextLine().trim();
        int total = readInt(scanner, "Total seats: ");
        int avail = readInt(scanner, "Available seats: ");
        double fare = readDouble(scanner, "Fare: ");
        system.createRide(src, dst, total, avail, fare);
        System.out.println("Ride created.");
    }

    private static void listRides(RideBookingSystem system) {
        ArrayList<Ride> rides = system.getRides();
        if (rides.isEmpty()) {
            System.out.println("No rides available.");
            return;
        }
        System.out.println("Rides:");
        for (int i = 0; i < rides.size(); i++) {
            System.out.println(i + ": " + rides.get(i));
        }
    }

    private static void bookRide(Scanner scanner, RideBookingSystem system) {
        if (system.getUsers().isEmpty()) {
            System.out.println("No users found. Please register a user first.");
            return;
        }
        if (system.getRides().isEmpty()) {
            System.out.println("No rides found. Please create a ride first.");
            return;
        }

        System.out.println("Select user by id:");
        for (User u : system.getUsers()) {
            System.out.println("- " + u.getId() + ": " + u.getName() + " (" + u.getContact() + ")");
        }
        int userId = readInt(scanner, "User id: ");
        User user = system.getUsers().stream()
                .filter(u -> u.getId() == userId)
                .findFirst()
                .orElse(null);
        if (user == null) {
            System.out.println("Invalid user id.");
            return;
        }

        listRides(system);
        int rideIndex = readInt(scanner, "Select ride index: ");
        int seats = readInt(scanner, "Seats to book: ");
        Booking booking = system.bookRide(user, rideIndex, seats);
        System.out.println("Booked successfully: " + booking);
    }

    private static void listBookings(RideBookingSystem system) {
        ArrayList<Booking> bookings = system.getBookings();
        if (bookings.isEmpty()) {
            System.out.println("No bookings yet.");
            return;
        }
        System.out.println("Bookings:");
        for (Booking b : bookings) {
            System.out.println("- " + b);
        }
    }

    private static void UpdateSeatsBooked(Scanner scanner, RideBookingSystem system) {
        int bookingID = readInt(scanner, "Booking ID: ");

        System.out.print("Enter additional Seats: ");
        int seats = scanner.nextInt();
        system.updateBooking(bookingID, seats);
        System.out.println("Booking updated.");
    }


    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException ignored) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private static double readDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine().trim();
            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException ignored) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static void seedSampleData(RideBookingSystem system) {
        system.createRide("Rajiv Chowk", "ISBT", 4, 4, 45.00);
        system.createRide("San Francisco", "Los Angeles", 50, 12, 45.50);
        system.createRide("Downtown", "Airport", 4, 3, 25.00);
        system.createRide("Suburbs", "Tech Park", 6, 6, 15.75);
        system.createRide("Boston", "New York City", 15, 8, 35.00);
        system.createRide("Hotel Plaza", "Convention Center", 2, 1, 120.00);
    }
}