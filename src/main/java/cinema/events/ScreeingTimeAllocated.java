package cinema.events;

import cinema.domain.Room;
import lombok.Value;

@Value
public class ScreeingTimeAllocated extends Event {
    Room room;
}
