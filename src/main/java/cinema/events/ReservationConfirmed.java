package cinema.events;

import cinema.domain.Customer;
import lombok.Value;

import java.util.UUID;

@Value
public class ReservationConfirmed implements Event {
    UUID screeningTimeId;
    UUID reservationId;

    @Override
    public UUID getAggregateRootId() {
        return screeningTimeId;
    }
}
