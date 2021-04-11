package cinema.events;

import cinema.domain.Movie;
import cinema.domain.Room;
import cinema.domain.SchedulingTime;
import lombok.Value;

import java.util.UUID;

@Value
public class PlannedScreeningCreated implements Event {
    UUID id;
    Movie movie;
    SchedulingTime schedulingTime;
    Room room;

    @Override
    public UUID getAggregateId() {
        return id;
    }
}
