package cinema;

import cinema.domain.Movie;
import cinema.domain.SchedulingTime;

public class ScreeningTimeScheduled extends Event {

    public final SchedulingTime schedulingTime;
    public final Movie movie;

    public ScreeningTimeScheduled(SchedulingTime schedulingTime, Movie movie) {
        this.schedulingTime = schedulingTime;
        this.movie = movie;
    }

}
