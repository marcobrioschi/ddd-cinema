package cinema.integration;

import cinema.command.ReserveSeats;
import cinema.events.PlannedScreeingAllocated;
import cinema.events.PlannedScreeningCreated;
import cinema.events.PlannedScreeningScheduled;
import cinema.query.AskForReservedSeats;
import cinema.readmodel.ReservedSeats;
import org.junit.jupiter.api.Test;
import testframework.BDDBaseTest;

import java.util.Arrays;

import static testframework.TestEnvironment.*;

public class IntegrationsTestWithCommandsAndQuery extends BDDBaseTest {


    @Test
    public void theReservationIsPaid() {

        Given(
                new PlannedScreeningCreated(Planned_Screening_ID),
                new PlannedScreeingAllocated(Red_Room),
                new PlannedScreeningScheduled(Thor_Ragnarok, Scheduling_At_15_Of_May_2021_At_4_00_PM)
        );

        When(
                NowIs_01_Of_May_2021_At_4_30_PM,
                new ReserveSeats(Jane_Brown,Planned_Screening_ID, Arrays.asList(Seat_A1))
        );

        Query(
                new AskForReservedSeats(Jane_Brown)
        );

        Then(
                new ReservedSeats.ReservedSeatsResults(Planned_Screening_ID, Arrays.asList(Seat_A1))
        );

    }

}
