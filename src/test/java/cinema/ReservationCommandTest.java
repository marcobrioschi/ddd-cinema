package cinema;

import cinema.domain.*;
import cinema.events.*;
import cinema.repository.InMemoryTestEventPusher;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ReservationCommandTest {

    @Test
    void successfulReservation() {

        UUID uuid = UUID.randomUUID();
        Movie movie = new Movie("Start Trek");
        Customer customer = new Customer("James", "Kirk");
        Room room = new Room("Enterprise");
        List<Seat> seats = Arrays.asList(new Seat("A", 1), new Seat("A", 2));
        SchedulingTime schedulingTime = new SchedulingTime(new Date());

        // TODO create eventrepository interface
        List<Event> eventStore = Arrays.asList(
                new ScreeningTimeCreated(uuid),
                new ScreeningTimeScheduled(schedulingTime, movie),
                new ScreeingTimeAllocated(room)
        );

        InMemoryTestEventPusher eventPusher = new InMemoryTestEventPusher();

        ReservationCommand reservationCommand = new ReservationCommand(customer, uuid, seats);
        CommandHandler commandHandler = new CommandHandler(eventStore, eventPusher);
        commandHandler.handle(reservationCommand);

        // TODO: use a better notation
//        assertThat(eventPusher.getEvents(), is(Arrays.asList(
//            new SeatsReserved(customer, seats, uuid, new ExpirationTime(new Da));
//        )));

        List<Event> results = eventPusher.getEvents();
        assertTrue(results.size() == 1);
        assertTrue(results.get(0) instanceof SeatsReserved);
        assertTrue(((SeatsReserved)results.get(0)).getSeats().containsAll(reservationCommand.getSeats()));

    }

    @Test
    void failedReservation() {

        UUID uuid = UUID.randomUUID();
        Movie movie = new Movie("Start Trek");
        Customer customer = new Customer("James", "Kirk");
        Room room = new Room("Enterprise");
        List<Seat> seats = Arrays.asList(new Seat("A", 1), new Seat("A", 2));
        SchedulingTime schedulingTime = new SchedulingTime(new Date());

        List<Event> eventStore = Arrays.asList(
                new ScreeningTimeCreated(uuid),
                new ScreeningTimeScheduled(schedulingTime, movie),
                new ScreeingTimeAllocated(room),
                new SeatsReserved(customer, seats, uuid, new ExpirationTime(new Date()))
        );

        InMemoryTestEventPusher eventPusher = new InMemoryTestEventPusher();

        ReservationCommand reservationCommand = new ReservationCommand(customer, uuid, seats);
        CommandHandler commandHandler = new CommandHandler(eventStore, eventPusher);
        commandHandler.handle(reservationCommand);

        // TODO: use a better notation
//        assertThat(eventPusher.getEvents(), is(Arrays.asList(
//            new SeatsReserved(customer, seats, uuid, new ExpirationTime(new Da));
//        )));

        List<Event> results = eventPusher.getEvents();
        assertTrue(results.size() == 1);
        assertTrue(results.get(0) instanceof FailedReservation);
        //assertTrue(((SeatsReserved)results.get(0)).seats.containsAll(reservationCommand.getSeats()));

    }

}
