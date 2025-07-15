import java.util.*;

class Movie {
    int id;
    String title;
    boolean[] seats;

    Movie(int id, String title, int totalSeats) {
        this.id = id;
        this.title = title;
        this.seats = new boolean[totalSeats]; // false = available, true = booked
    }

    void showSeats() {
        System.out.println("\n!!! Seat Map for \"" + title + "\" !!!");
        for (int i = 0; i < seats.length; i++) {
            String seatDisplay = seats[i] ? "[X]" : String.format("[%2d]", i + 1);
            System.out.print(seatDisplay + " ");
        }
        System.out.println("\n");
    }

    boolean bookSeat(int seatNumber) {
        if (seatNumber < 1 || seatNumber > seats.length) {
            System.out.println("!!! Invalid seat number. !!!");
            return false;
        }
        if (seats[seatNumber - 1]) {
            System.out.println("!!! Seat is already booked. !!!");
            return false;
        }
        seats[seatNumber - 1] = true;
        return true;
    }

    public String toString() {
        return id + ". " + title + " (" + seats.length + " seats)";
    }
}

class BookingSystem {
    Map<Integer, Movie> movies = new HashMap<>();
    Scanner scanner = new Scanner(System.in);

    void loadMovies() {
        movies.put(1, new Movie(1, "Avengers: Endgame", 10));
        movies.put(2, new Movie(2, "F1: The Movie", 8));
        movies.put(3, new Movie(3, "Pirates of the Caribbean: The Curse of the Black Pearl", 12));
    }

    void start() {
        loadMovies();

        while (true) {
            System.out.println("\n!!! Available Movies !!!");
            for (Movie m : movies.values()) System.out.println("   " + m);
            System.out.println("   0. Exit");

            System.out.print("\n!!! Choose a movie by ID: ");
            int movieId = readInt();
            if (movieId == 0) break;

            Movie movie = movies.get(movieId);
            if (movie == null) {
                System.out.println("!!! Movie not found. !!!");
                continue;
            }

            movie.showSeats();
            System.out.print("!!! Enter seat number to book (0 to cancel): ");
            int seatNumber = readInt();
            if (seatNumber == 0) continue;

            if (movie.bookSeat(seatNumber)) {
                System.out.println("!!! Seat " + seatNumber + " successfully booked for \"" + movie.title + "\" !!!");
            }
        }

        System.out.println("\n!!! Thanks for using the booking system! !!!");
    }

    int readInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("!!! Invalid input. Please enter a number: ");
            }
        }
    }
}

public class MovieTicketBooking {
    public static void main(String[] args) {
        new BookingSystem().start();
    }
}
