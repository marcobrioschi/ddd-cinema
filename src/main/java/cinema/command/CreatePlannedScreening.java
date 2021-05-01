package cinema.command;

import cinema.domain.Movie;
import cinema.domain.Room;
import cinema.domain.SchedulingTime;
import lombok.Value;

import java.util.UUID;

@Value
public class CreatePlannedScreening implements Command {
    Movie movie;
    SchedulingTime schedulingTime;
    Room room;

    @Override
    public UUID getTargetEntityId() {
        return null;
    }
}
