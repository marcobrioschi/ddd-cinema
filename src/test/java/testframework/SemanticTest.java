package testframework;

import cinema.domain.Customer;
import cinema.domain.Movie;
import cinema.domain.Room;
import cinema.domain.Seat;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.UUID;

// TODO this is only a way to share code, there is a better solution?
public class SemanticTest extends BDDBaseTest {

    // Entity ID

    protected UUID Planned_Screening_ID() {
        return UUID.fromString("02fece25-c347-4383-9a51-00a0b42b7238");
    }


    // Value objects

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


    // DateTime values

    protected LocalDateTime At_01_Of_May_2021_At_4_30_PM() {
        return LocalDateTime.of(2021, Month.MAY, 01, 16, 30);
    }

    protected LocalDateTime At_15_Of_May_2021_At_6_00_PM() {
        return LocalDateTime.of(2021, Month.MAY, 15, 18, 00);
    }

    protected LocalDateTime At_01_Of_May_2021_At_4_42_PM() {
        return LocalDateTime.of(2021, Month.MAY, 01, 16, 42);
    }

    protected LocalDateTime At_01_Of_May_2021_At_4_50_PM() {
        return LocalDateTime.of(2021, Month.MAY, 01, 16, 50);
    }

}
