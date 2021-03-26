package cinema.events;

import lombok.Value;

import java.util.UUID;

@Value
public class ScreeningTimeCreated extends Event {
    public final UUID id;
}
