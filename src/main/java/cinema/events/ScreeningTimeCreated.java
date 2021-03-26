package cinema.events;

import cinema.events.Event;
import lombok.Value;

import java.util.UUID;

@Value
public class ScreeningTimeCreated extends Event {
    public final UUID id;
}
