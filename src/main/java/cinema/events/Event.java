package cinema.events;

import java.util.UUID;

public interface Event {
    // TODO NOTE: the easier solution to not pollute the code and use Lombok for Value(s)
    UUID getAggregateId();
}
