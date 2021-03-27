package cinema.events;

import cinema.domain.Movie;
import cinema.domain.SchedulingTime;
import lombok.Value;

@Value
public class PlannedScreeningScheduled extends Event {
    Movie movie;
    SchedulingTime schedulingTime;
}
