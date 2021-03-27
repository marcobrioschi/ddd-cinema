package cinema.events;

import cinema.domain.Customer;
import cinema.domain.RefusedReservationReason;
import cinema.domain.Seat;
import lombok.Value;

import java.util.List;

@Value
public class ReservationFailed implements Event {
    Customer customer;
    List<Seat> seats;
    RefusedReservationReason reason;
}
