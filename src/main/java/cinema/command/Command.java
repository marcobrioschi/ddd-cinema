package cinema.command;

import java.util.UUID;

public interface Command {
    UUID getTargetEntityId();
}
