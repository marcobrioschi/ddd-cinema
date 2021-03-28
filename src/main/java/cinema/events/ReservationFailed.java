package cinema.events;

import cinema.domain.*;
import lombok.Value;

import java.util.List;

@Value
public class ReservationFailed implements Event {
    Customer customer;
    List<Seat> seats;
    RefusedReservationReason reason;

    public static ReservationFailed ReservationFailed(Customer customer, List<Seat> seats, RefusedReservationReason reason) {
        return new ReservationFailed(customer, seats, reason);
    }
}
