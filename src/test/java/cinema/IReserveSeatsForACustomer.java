package cinema;

import cinema.command.ReservationCommand;
import cinema.domain.ExpirationTime;
import cinema.domain.Movie;
import cinema.domain.RefusedReservationReason;
import cinema.domain.SchedulingTime;
import cinema.events.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;


public class IReserveSeatsForACustomer extends SemanticTest {

    @Test
    void TheReservationCompleteSuccessfully() {

        UUID uuid = UUID.randomUUID();
        LocalDateTime frozenNow = LocalDateTime.now();
        SchedulingTime schedulingTime = new SchedulingTime(frozenNow);

        Given(
                new PlannedScreeningCreated(uuid),
                new PlannedScreeningScheduled(The_Wolf_of_Wall_Street(), schedulingTime),
                new PlannedScreeingAllocated(Red_Room())
        );

        When(
                new ReservationCommand(John_Smith(), uuid, Arrays.asList(Seat_A1(), Seat_A2()))
        );

        Then(
                new SeatsReserved(John_Smith(), Arrays.asList(Seat_A1(), Seat_A2()), uuid, new ExpirationTime(frozenNow))
        );

    }

    @Test
    void OneOfTheChosenSeatsIsNotAvailable() {

        UUID uuid = UUID.randomUUID();
        Movie movie = The_Wolf_of_Wall_Street();
        LocalDateTime frozenNow = LocalDateTime.now();
        SchedulingTime schedulingTime = new SchedulingTime(frozenNow);

        Given(
                new PlannedScreeningCreated(uuid),
                new PlannedScreeningScheduled(movie, schedulingTime),
                new PlannedScreeingAllocated(Red_Room()),
                new SeatsReserved(John_Smith(), Arrays.asList(Seat_A1(), Seat_A2()), uuid, new ExpirationTime(frozenNow))
        );

        When(
                new ReservationCommand(Jane_Brown(), uuid, Arrays.asList(Seat_A1()))
        );

        Then(
                new ReservationFailed(Jane_Brown(), Arrays.asList(Seat_A1()), RefusedReservationReason.SEATS_ALREADY_RESERVED)
        );

    }

    void RequestIsTooCloseToTheMovieBeginning() {
        // TODO, max 15 minutes
    }

    void DueToCovid19TheNearestSeatsMustToBeFree() {

    }

    // TODO adult, student, child with varying prices

    // TODO 3D Movies and D-Box Seats cost extra. For students the price gets procentually redacted.

}
