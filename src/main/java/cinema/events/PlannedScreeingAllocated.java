package cinema.events;

import cinema.domain.Room;
import lombok.Value;

import java.util.UUID;

@Value
public class PlannedScreeingAllocated implements Event {
    Room room;

    public static PlannedScreeingAllocated PlannedScreeingAllocated(Room room) {
        return new PlannedScreeingAllocated(room);
    }
}
