package cinema.command;

import cinema.domain.RefusedReservationReasons;
import org.junit.jupiter.api.Test;
import testframework.BDDBaseTest;

import java.util.Arrays;

import static cinema.command.ReserveSeats.ReservationCommand;
import static cinema.events.PlannedScreeingAllocated.PlannedScreeingAllocated;
import static cinema.events.PlannedScreeningCreated.PlannedScreeningCreated;
import static cinema.events.PlannedScreeningScheduled.PlannedScreeningScheduled;
import static cinema.events.ReservationFailed.ReservationFailed;
import static cinema.events.SeatsReserved.SeatsReserved;
import static testframework.TestEnvironment.*;

public class ReserveSeatsTest extends BDDBaseTest {

    @Test
    void TheReservationCompleteSuccessfully() {

        Given(
                PlannedScreeningCreated(Planned_Screening_ID),
                PlannedScreeningScheduled(The_Wolf_of_Wall_Street, Scheduling_At_15_Of_May_2021_At_6_00_PM),
                PlannedScreeingAllocated(Red_Room)
        );

        When(
                NowIs_01_Of_May_2021_At_4_30_PM,
                ReservationCommand(John_Smith, Planned_Screening_ID, Arrays.asList(Seat_A1, Seat_A2))
        );

        Then(
                SeatsReserved(
                        John_Smith,
                        Arrays.asList(Seat_A1, Seat_A2),
                        Planned_Screening_ID,
                        Expire_At_01_Of_May_2021_At_4_42_PM
                )
        );

    }

    @Test
    void OneOfTheChosenSeatsIsNotAvailable() {

        Given(
                PlannedScreeningCreated(Planned_Screening_ID),  // TODO: the is provided by the input command?
                PlannedScreeningScheduled(The_Wolf_of_Wall_Street, Scheduling_At_15_Of_May_2021_At_6_00_PM),
                PlannedScreeingAllocated(Red_Room),
                SeatsReserved(John_Smith, Arrays.asList(Seat_A1, Seat_A2), Planned_Screening_ID, Expire_At_01_Of_May_2021_At_4_42_PM)
        );

        When(
                NowIs_01_Of_May_2021_At_4_50_PM,
                ReservationCommand(Jane_Brown, Planned_Screening_ID, Arrays.asList(Seat_A1))
        );

        Then(
                ReservationFailed(Jane_Brown, Arrays.asList(Seat_A1), RefusedReservationReasons.SEATS_ALREADY_RESERVED)
        );

    }

    @Test
    void RequestIsTooCloseToTheMovieBeginning() {

        Given(
                PlannedScreeningCreated(Planned_Screening_ID),
                PlannedScreeningScheduled(The_Wolf_of_Wall_Street, Scheduling_At_15_Of_May_2021_At_6_00_PM),
                PlannedScreeingAllocated(Red_Room)
        );

        When(
                NowIs_15_Of_May_2021_At_5_45_PM,
                ReservationCommand(John_Smith, Planned_Screening_ID, Arrays.asList(Seat_A2))
        );

        Then(
                ReservationFailed(John_Smith, Arrays.asList(Seat_A2), RefusedReservationReasons.RESERVATION_TOO_CLOSE_TO_SCREENING_START)
        );

    }

}
