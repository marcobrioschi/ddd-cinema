package cinema.events;

import cinema.domain.Customer;
import cinema.domain.ExpirationTime;
import cinema.domain.Seat;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
public class SeatsReserved implements Event {
    UUID screeningTimeId;
    UUID reservationId;
    Customer customer;
    List<Seat> seats;
    ExpirationTime expirationTime;

    @Override
    public UUID getAggregateRootId() {
        return screeningTimeId;
    }
}
