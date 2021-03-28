package cinema.events;

import cinema.domain.Movie;
import cinema.domain.SchedulingTime;
import lombok.Value;

@Value
public class PlannedScreeningScheduled implements Event {
    Movie movie;
    SchedulingTime schedulingTime;

    public static PlannedScreeningScheduled PlannedScreeningScheduled(Movie movie, SchedulingTime schedulingTime) {
        return new PlannedScreeningScheduled(movie, schedulingTime);
    }
}
