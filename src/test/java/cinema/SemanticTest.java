package cinema;

import cinema.domain.Customer;
import cinema.domain.Movie;
import cinema.domain.Room;
import cinema.domain.Seat;

// TODO this is only a way to share code, there is a better solution?
public class SemanticTest extends BDDBaseTest {

    protected Movie The_Wolf_of_Wall_Street() {
        return new Movie("The Wolf of Wall Street");
    }

    protected Room Red_Room() {
        return new Room("Red Room");
    }

    protected Seat Seat_A1() {
        return new Seat("A", 1);
    }

    protected Seat Seat_A2() {
        return new Seat("A", 2);
    }

    protected Customer John_Smith() {
        return new Customer("John", "Smith");
    }

    protected Customer Jane_Brown() {
        return new Customer("Jane", "Brown");
    }

}
