package cinema.readmodel;


import org.junit.jupiter.api.Test;
import testframework.BDDBaseTest;

import java.util.Arrays;

import static testframework.CinemaUtils.*;
import static testframework.TestScenario.*;


public class MovieListTest extends BDDBaseTest {

    @Test
    public void IWantToListMoviesInASpecificDateTime_NarrowRange() {

        Given(
                PlannedScreeningCreated(Planned_Screening_ID1, The_Wolf_of_Wall_Street, Scheduling_At_15_Of_May_2021_At_4_00_PM, Red_Room),
                PlannedScreeningCreated(Planned_Screening_ID2, Thor_Ragnarok, Scheduling_At_15_Of_May_2021_At_5_00_PM, Blue_Room),
                PlannedScreeningCreated(Planned_Screening_ID3, Guardian_Of_The_Galaxy, Scheduling_At_15_Of_May_2021_At_6_00_PM, Yellow_Room)
        );

        Query(
                MovieListInTimeWindow(T_15_Of_May_2021_At_4_05_PM, T_15_Of_May_2021_At_5_58_PM)
        );

        Then(
                MovieListAnswer(
                        Arrays.asList(
                            MovieListEntry(Planned_Screening_ID2, Scheduling_At_15_Of_May_2021_At_5_00_PM, Thor_Ragnarok)
                        )
                )
        );

    }

    @Test
    public void IWantToListMoviesInASpecificDateTime_FullRange() {

        Given(
                PlannedScreeningCreated(Planned_Screening_ID1, Guardian_Of_The_Galaxy, Scheduling_At_15_Of_May_2021_At_4_00_PM, Red_Room),
                PlannedScreeningCreated(Planned_Screening_ID2, Thor_Ragnarok, Scheduling_At_15_Of_May_2021_At_5_00_PM, Blue_Room),
                PlannedScreeningCreated(Planned_Screening_ID3, Guardian_Of_The_Galaxy, Scheduling_At_15_Of_May_2021_At_6_00_PM, Yellow_Room)
        );

        Query(
                MovieListInTimeWindow(T_15_Of_May_2021_At_3_30_PM, T_15_Of_May_2021_At_7_30_PM)
        );

        Then(
                MovieListAnswer(
                        Arrays.asList(
                                MovieListEntry(Planned_Screening_ID1, Scheduling_At_15_Of_May_2021_At_4_00_PM, Guardian_Of_The_Galaxy),
                                MovieListEntry(Planned_Screening_ID2, Scheduling_At_15_Of_May_2021_At_5_00_PM, Thor_Ragnarok),
                                MovieListEntry(Planned_Screening_ID3, Scheduling_At_15_Of_May_2021_At_6_00_PM, Guardian_Of_The_Galaxy)
                        )
                )
        );

    }

}