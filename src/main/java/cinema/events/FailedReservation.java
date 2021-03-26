package cinema.events;

import cinema.domain.Customer;
import cinema.domain.RefusedReservationReason;
import cinema.domain.Seat;
import cinema.events.Event;
import lombok.Value;

import java.util.List;

@Value
public class FailedReservation extends Event {
    Customer customer;
    List<Seat> seats;
    RefusedReservationReason reason;
}
