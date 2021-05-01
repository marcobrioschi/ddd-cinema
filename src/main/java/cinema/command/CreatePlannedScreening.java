package cinema.command;

import cinema.domain.Movie;
import cinema.domain.Room;
import cinema.domain.SchedulingTime;
import lombok.Value;

@Value
public class CreatePlannedScreening implements Command {
    Movie movie;
    SchedulingTime schedulingTime;
    Room room;
}
