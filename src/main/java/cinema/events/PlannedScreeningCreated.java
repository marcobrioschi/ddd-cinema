package cinema.events;

import lombok.Value;

import java.util.UUID;

@Value
public class PlannedScreeningCreated extends Event {
    public final UUID id;
}
