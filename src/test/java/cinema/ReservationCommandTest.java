package cinema;

import cinema.domain.*;
import cinema.events.*;
import cinema.repository.InMemoryTestEventPusher;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class ReservationCommandTest {

    @Test
    void successfulReservation() {

        UUID uuid = UUID.randomUUID();
        Movie movie = new Movie("Start Trek");
        Customer customer = new Customer("James", "Kirk");
        Room room = new Room("Enterprise");
        List<Seat> seats = Arrays.asList(new Seat("A", 1), new Seat("A", 2));
        Date frozenNow = new Date();
        SchedulingTime schedulingTime = new SchedulingTime(frozenNow);

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

        assertThat(eventPusher.getEvents(), is(Arrays.asList(
                new SeatsReserved(customer, seats, uuid, new ExpirationTime(frozenNow))
        )));

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

        assertThat(eventPusher.getEvents(), is(Arrays.asList(
                new FailedReservation(customer, seats, RefusedReservationReason.SEATS_ALREADY_RESERVED)
        )));

    }

}
