package cinema.events;

import cinema.domain.Movie;
import cinema.domain.SchedulingTime;
import lombok.Value;

@Value
public class ScreeningTimeScheduled extends Event {
    SchedulingTime schedulingTime;
    Movie movie;
}
