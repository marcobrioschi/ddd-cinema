package cinema.events;

import lombok.Value;

import java.util.UUID;

@Value
public class PlannedScreeningCreated implements Event {
    public final UUID id;
}
