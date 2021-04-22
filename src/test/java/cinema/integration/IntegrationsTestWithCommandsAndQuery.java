package cinema.integration;

import org.junit.jupiter.api.Test;
import testframework.BDDBaseTest;

import java.util.Arrays;

import static testframework.CinemaUtils.*;
import static testframework.TestScenario.*;

public class IntegrationsTestWithCommandsAndQuery extends BDDBaseTest {

    @Test
    public void theReservationIsPaid() {

        Given(
                PlannedScreeningCreated(Planned_Screening_ID1, Thor_Ragnarok, Scheduling_At_15_Of_May_2021_At_4_00_PM, Red_Room)
        );

        When(
                NowIs_01_Of_May_2021_At_4_30_PM,
                ReserveSeats(Jane_Brown, Planned_Screening_ID1, Arrays.asList(Seat_A1))
        );

        Query(
                AskForReservedSeats(Jane_Brown)
        );

        Then(
                ReservedSeatsAnswer(
                        Arrays.asList(
                            ReservedSeatsEntry(
                                    Planned_Screening_ID1,
                                    Jane_Brown,
                                    Scheduling_At_15_Of_May_2021_At_4_00_PM,
                                    Thor_Ragnarok,
                                    Arrays.asList(Seat_A1)
                            )
                        )
                )
        );

    }

}
