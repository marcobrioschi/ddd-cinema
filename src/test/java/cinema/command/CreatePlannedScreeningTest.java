package cinema.command;

import org.junit.jupiter.api.Test;
import testframework.BDDBaseTest;
import testframework.TestScenario;

import static testframework.CinemaUtils.CreatePlannedScreening;
import static testframework.CinemaUtils.PlannedScreeningCreated;
import static testframework.TestScenario.*;

public class CreatePlannedScreeningTest extends BDDBaseTest {

    @Test
    public void createPlanningScreen() {

        Given(null);

        When(
                TestScenario.NowIs_01_Of_May_2021_At_4_30_PM,
                CreatePlannedScreening(The_Wolf_of_Wall_Street, Scheduling_At_15_Of_May_2021_At_6_00_PM, Red_Room)
        );

        Then(
             PlannedScreeningCreated(NextGenerated_ID1, The_Wolf_of_Wall_Street, Scheduling_At_15_Of_May_2021_At_6_00_PM, Red_Room)
        );

    }

}
