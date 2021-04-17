package cinema.readmodel;

import org.junit.jupiter.api.Test;
import testframework.BDDBaseTest;
import testframework.CinemaUtils;

import java.util.Arrays;

import static testframework.CinemaUtils.*;
import static testframework.TestScenario.*;

public class ReservedSeatsTest extends BDDBaseTest {

    @Test
    public void IHaventReservedSeats() {

        Given(
                PlannedScreeningCreated(Planned_Screening_ID1, The_Wolf_of_Wall_Street, Scheduling_At_15_Of_May_2021_At_4_00_PM, Red_Room),
                PlannedScreeningCreated(Planned_Screening_ID2, Thor_Ragnarok, Scheduling_At_15_Of_May_2021_At_5_00_PM, Blue_Room),
                CinemaUtils.SeatsReserved(Jane_Brown, Arrays.asList(Seat_A1), Planned_Screening_ID1, Expire_At_01_Of_May_2021_At_4_42_PM)
        );

        Query(
                AskForReservedSeats(John_Smith)
        );

        Then(
                ReservedSeatsAnswer(
                        Arrays.asList()
                )
        );

    }

    @Test
    public void IWantToSeeMyReservedSeats() {

        Given(
                PlannedScreeningCreated(Planned_Screening_ID1, The_Wolf_of_Wall_Street, Scheduling_At_15_Of_May_2021_At_4_00_PM, Red_Room),
                PlannedScreeningCreated(Planned_Screening_ID2, Thor_Ragnarok, Scheduling_At_15_Of_May_2021_At_5_00_PM, Blue_Room),
                CinemaUtils.SeatsReserved(Jane_Brown, Arrays.asList(Seat_A1), Planned_Screening_ID1, Expire_At_01_Of_May_2021_At_4_42_PM)
        );

        Query(
                AskForReservedSeats(Jane_Brown)
        );

        Then(
                ReservedSeatsAnswer(
                        Arrays.asList(
                                ReservedSeatsEntry(Planned_Screening_ID1, Jane_Brown, Arrays.asList(Seat_A1))
                        )
                )
        );

    }

}
