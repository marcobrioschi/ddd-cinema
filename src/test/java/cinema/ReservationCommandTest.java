package cinema;

import cinema.domain.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ReservationCommandTest {

    @Test
    void successfulReservation() {

        Movie movie = new Movie("Start Trek");
        Customer customer = new Customer("James", "Kirk");
        Room room = new Room("Enterprise");
        List<Seat> seats = Arrays.asList(new Seat(room, "A", 1));
        SchedulingTime schedulingTime = new SchedulingTime(new Date());

    	ScreeningTime _screeningTime = new ScreeningTime(UUID.randomUUID(), movie, room, schedulingTime, new ArrayList<Seat>());
        CommandHandler commandHandler = new CommandHandler(_screeningTime);

        ReservationCommand reservationCommand = new ReservationCommand(customer, _screeningTime, seats);
        commandHandler.handle(reservationCommand);

        System.out.println(reservationCommand.getSeats());
        System.out.println(_screeningTime.getReservedSeats());

        assertTrue(_screeningTime.getReservedSeats().containsAll(reservationCommand.getSeats()));

    }

}
