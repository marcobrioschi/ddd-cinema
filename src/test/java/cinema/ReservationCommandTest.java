package cinema;

import cinema.domain.*;
import cinema.events.*;
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
        List<Seat> seats = Arrays.asList(new Seat("A", 1), new Seat("A", 2));
        SchedulingTime schedulingTime = new SchedulingTime(new Date());
        UUID uuid = UUID.randomUUID();

        List<Event> eventStore = Arrays.asList(
                new ScreeningTimeCreated(uuid),
                new ScreeningTimeScheduled(schedulingTime, movie),
                new ScreeingTimeAllocated(room)
        );

        ScreeningTime screeningTime = new ScreeningTime(eventStore);

        InMemoryTestPusher cinema2 = new InMemoryTestPusher();

        ReservationCommand reservationCommand = new ReservationCommand(customer, screeningTime, seats);
        CommandHandler commandHandler = new CommandHandler(eventStore, cinema2);

        List<Event> results = commandHandler.handle(reservationCommand);

        assertTrue(results.size() == 1);
        assertTrue(results.get(0) instanceof SeatsReserved);
        assertTrue(((SeatsReserved)results.get(0)).getSeats().containsAll(reservationCommand.getSeats()));

        // TODO: use a better notation
//        assertThat(cinema2.getEvents(), is(Arrays.asList(
//            new SeatsReserved(customer, seats, uuid, new ExpirationTime(new Da));
//        )));

        results = cinema2.getEvents();
        assertTrue(results.size() == 1);
        assertTrue(results.get(0) instanceof SeatsReserved);
        assertTrue(((SeatsReserved)results.get(0)).getSeats().containsAll(reservationCommand.getSeats()));

    }

    @Test
    void failedReservation() {

        Movie movie = new Movie("Start Trek");
        Customer customer = new Customer("James", "Kirk");
        Room room = new Room("Enterprise");
        List<Seat> seats = Arrays.asList(new Seat("A", 1), new Seat("A", 2));
        SchedulingTime schedulingTime = new SchedulingTime(new Date());

        UUID uuid = UUID.randomUUID();
        List<Event> eventStore = Arrays.asList(
                new ScreeningTimeCreated(uuid),
                new ScreeningTimeScheduled(schedulingTime, movie),
                new ScreeingTimeAllocated(room),
                new SeatsReserved(customer, seats, uuid, new ExpirationTime(new Date()))
        );
        ScreeningTime _screeningTime = new ScreeningTime(eventStore);


        InMemoryTestPusher cinema2 = new InMemoryTestPusher();

        ReservationCommand reservationCommand = new ReservationCommand(customer, _screeningTime, seats);
        CommandHandler commandHandler = new CommandHandler(eventStore, cinema2);

        List<Event> results = commandHandler.handle(reservationCommand);

        assertTrue(results.size() == 1);
        assertTrue(results.get(0) instanceof FailedReservation);

        // TODO: use a better notation
//        assertThat(cinema2.getEvents(), is(Arrays.asList(
//            new SeatsReserved(customer, seats, uuid, new ExpirationTime(new Da));
//        )));

        results = cinema2.getEvents();
        assertTrue(results.size() == 1);
        assertTrue(results.get(0) instanceof FailedReservation);
        //assertTrue(((SeatsReserved)results.get(0)).seats.containsAll(reservationCommand.getSeats()));

    }

}
