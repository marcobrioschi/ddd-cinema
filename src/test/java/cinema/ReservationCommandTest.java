package cinema;

import cinema.domain.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class ReservationCommandTest {

    @Test
    void successfulReservation() {

        Movie movie = new Movie("Start Trek");
        Customer customer = new Customer("James", "Kirk");
        Room room = new Room("Enterprise");
        List<Seat> seats = Arrays.asList(new Seat("A", 1), new Seat("A", 2));
        SchedulingTime schedulingTime = new SchedulingTime(new Date());

        UUID uuid = UUID.randomUUID();
        //ScreeningTime _screeningTime = new ScreeningTime(uuid, movie, room, schedulingTime, new ArrayList<Seat>());
        List<Event> events = Arrays.asList(
                new ScreeningTimeCreated(uuid),
                new ScreeningTimeScheduled(schedulingTime, movie),
                new ScreeingTimeAllocated(room)
        );
        ScreeningTime _screeningTime = new ScreeningTime(events);
        ScreeningTimes cinema = new ScreeningTimeInMemoryStorage();
        cinema.save(_screeningTime);

        ReservationCommand reservationCommand = new ReservationCommand(customer, _screeningTime, seats);
        CommandHandler commandHandler = new CommandHandler(cinema);

        List<Event> results = commandHandler.handle(reservationCommand);

        assertTrue(results.size() == 1);
        assertTrue(results.get(0) instanceof SeatsReserved);
        assertTrue(((SeatsReserved)results.get(0)).seats.containsAll(reservationCommand.getSeats()));

    }

    // TODO: to test the failure of the command, the check on the repository isn't a solution
}
