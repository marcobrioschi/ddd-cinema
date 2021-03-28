package cinema.events;

import lombok.Value;

import java.util.UUID;

@Value
public class PlannedScreeningCreated implements Event {
    public final UUID id;

    public static PlannedScreeningCreated PlannedScreeningCreated(UUID id) {
       return new PlannedScreeningCreated(id);
    }
}
