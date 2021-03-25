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
        List<Seat> seats = Arrays.asList(new Seat(room, "A", 1), new Seat(room,"A", 2));
        SchedulingTime schedulingTime = new SchedulingTime(new Date());
        UUID uuid = UUID.randomUUID();
        ScreeningTime _screeningTime = new ScreeningTime(uuid, movie, room, schedulingTime, new ArrayList<Seat>());
        ReservationCommand reservationCommand = new ReservationCommand(customer, _screeningTime, seats);

        ScreeningTimes cinema = new ScreeningTimeInMemoryStorage();
        cinema.save(_screeningTime);

        CommandHandler commandHandler = new CommandHandler(cinema);

        commandHandler.handle(reservationCommand);

        assertTrue(cinema.findByUUID(uuid).getReservedSeats().containsAll(reservationCommand.getSeats()));

    }

    // TODO: to test the failure of the command, the check on the repository isn't a solution
}
