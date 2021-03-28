package cinema.events;

import cinema.domain.Customer;
import cinema.domain.ExpirationTime;
import cinema.domain.RefusedReservationReason;
import cinema.domain.Seat;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
public class SeatsReserved implements Event {
    Customer customer;
    List<Seat> seats;
    UUID screeningTimeId;
    ExpirationTime expirationTime;

    public static SeatsReserved SeatsReserved(Customer customer, List<Seat> seats, UUID screeningTimeId, ExpirationTime expirationTime) {
        return new SeatsReserved(customer, seats, screeningTimeId, expirationTime);
    }
}
