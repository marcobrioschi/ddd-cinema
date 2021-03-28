package testframework;

import cinema.domain.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.UUID;

public class TestEnvironment {

    // Entity ID

    public static final UUID Planned_Screening_ID = UUID.fromString("02fece25-c347-4383-9a51-00a0b42b7238");

    // Value objects

    public static final Movie The_Wolf_of_Wall_Street = new Movie("The Wolf of Wall Street");
    public static final Room Red_Room = new Room("Red Room");
    public static final Seat Seat_A1 = new Seat("A", 1);
    public static final Seat Seat_A2 = new Seat("A", 2);
    public static final Customer John_Smith = new Customer("John", "Smith");
    public static final Customer Jane_Brown = new Customer("Jane", "Brown");

    // DateTime values

    public static final LocalDateTime NowIs_01_Of_May_2021_At_4_30_PM = LocalDateTime.of(2021, Month.MAY, 01, 16, 30);
    public static final LocalDateTime NowIs_01_Of_May_2021_At_4_50_PM = LocalDateTime.of(2021, Month.MAY, 01, 16, 50);
    public static final LocalDateTime NowIs_15_Of_May_2021_At_5_45_PM = LocalDateTime.of(2021, Month.MAY, 15, 17, 45);
    public static final ExpirationTime Expire_At_01_Of_May_2021_At_4_42_PM = new ExpirationTime(
            LocalDateTime.of(2021, Month.MAY, 01, 16, 42)
    );
    public static final SchedulingTime Scheduling_At_15_Of_May_2021_At_6_00_PM = new SchedulingTime(
            LocalDateTime.of(2021, Month.MAY, 15, 18, 00)
    );

}
