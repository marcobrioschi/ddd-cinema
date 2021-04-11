package cinema.events;

import cinema.domain.Customer;
import cinema.domain.RefusedReservationReasons;
import cinema.domain.Seat;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
public class ReservationFailed implements Event {
    UUID screeningTimeId;
    Customer customer;
    List<Seat> seats;
    RefusedReservationReasons reason;

    @Override
    public UUID getAggregateId() {
        return screeningTimeId;
    }
}
