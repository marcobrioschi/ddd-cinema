package cinema;

import cinema.command.ReservationCommand;
import cinema.domain.ExpirationTime;
import cinema.domain.RefusedReservationReason;
import cinema.domain.SchedulingTime;
import cinema.events.*;
import org.junit.jupiter.api.Test;
import testframework.SemanticTest;

import java.util.Arrays;


public class IReserveSeatsForACustomer extends SemanticTest {

    @Test
    void TheReservationCompleteSuccessfully() {

        Given(
                new PlannedScreeningCreated(Planned_Screening_ID()),
                new PlannedScreeningScheduled(The_Wolf_of_Wall_Street(), new SchedulingTime(At_15_Of_May_2021_At_6_00_PM())),
                new PlannedScreeingAllocated(Red_Room())
        );

        When(
                At_01_Of_May_2021_At_4_30_PM(),
                new ReservationCommand(John_Smith(), Planned_Screening_ID(), Arrays.asList(Seat_A1(), Seat_A2()))
        );

        Then(
                new SeatsReserved(
                        John_Smith(),
                        Arrays.asList(Seat_A1(), Seat_A2()),
                        Planned_Screening_ID(),
                        new ExpirationTime(At_01_Of_May_2021_At_4_30_PM())
                )
        );

    }

    @Test
    void OneOfTheChosenSeatsIsNotAvailable() {

        Given(
                new PlannedScreeningCreated(Planned_Screening_ID()),
                new PlannedScreeningScheduled(The_Wolf_of_Wall_Street(), new SchedulingTime(At_15_Of_May_2021_At_6_00_PM())),
                new PlannedScreeingAllocated(Red_Room()),
                new SeatsReserved(John_Smith(), Arrays.asList(Seat_A1(), Seat_A2()), Planned_Screening_ID(), new ExpirationTime(At_01_Of_May_2021_At_4_30_PM()))
        );

        When(
                At_01_Of_May_2021_At_4_30_PM(),
                new ReservationCommand(Jane_Brown(), Planned_Screening_ID(), Arrays.asList(Seat_A1()))
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
