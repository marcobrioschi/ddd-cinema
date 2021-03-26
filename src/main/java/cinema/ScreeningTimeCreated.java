package cinema;

import java.util.UUID;

public class ScreeningTimeCreated extends Event {

    public final UUID id;
    public ScreeningTimeCreated(UUID id) {
        this.id = id;
    }

}
