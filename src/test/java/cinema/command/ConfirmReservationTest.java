package cinema.command;

import cinema.domain.RefusedConfirmationReasons;
import cinema.events.ConfirmFailed;
import cinema.events.PlannedScreeningCreated;
import cinema.events.ReservationConfirmed;
import cinema.events.SeatsReserved;
import org.junit.jupiter.api.Test;
import testframework.BDDBaseTest;

import java.util.Arrays;

import static testframework.TestScenario.*;
import static testframework.CinemaUtils.*;

public class ConfirmReservationTest extends BDDBaseTest {

    @Test
    public void TheReservationIsConfirmedOnTime() {

        Given(
                PlannedScreeningCreated(Planned_Screening_ID1, The_Wolf_of_Wall_Street, Scheduling_At_15_Of_May_2021_At_6_00_PM, Blue_Room),
                SeatsReserved(Planned_Screening_ID1, Reservation_ID1, John_Smith, Arrays.asList(Seat_A2), Expire_At_01_Of_May_2021_At_4_42_PM)
        );

        When(
                NowIs_01_Of_May_2021_At_4_30_PM,
                ConfirmReservation(Planned_Screening_ID1, Reservation_ID1)
        );

        Then(
                ReservationConfirmed(Planned_Screening_ID1, Reservation_ID1)
        );

    }

    //@Test
    // TODO: implements the logic
    public void TheConfirmFailBecauseTheReservationIsExpired() {

        Given(
                PlannedScreeningCreated(Planned_Screening_ID1, The_Wolf_of_Wall_Street, Scheduling_At_15_Of_May_2021_At_6_00_PM, Blue_Room),
                SeatsReserved(Planned_Screening_ID1, Reservation_ID1, John_Smith, Arrays.asList(Seat_A2), Expire_At_01_Of_May_2021_At_4_42_PM)
        );

        When(
                NowIs_01_Of_May_2021_At_4_50_PM,
                ConfirmReservation(Planned_Screening_ID1, Reservation_ID1)
        );

        Then(
                ConfirmFailed(Planned_Screening_ID1, Reservation_ID1, John_Smith, Arrays.asList(Seat_A2), RefusedConfirmationReasons.RESERVATION_WAS_EXPIRED)
        );

    }

}
