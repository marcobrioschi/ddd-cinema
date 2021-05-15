package cinema.domain;

import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
public class Reservation {
    UUID reservationId;
    Customer customer;
    List<Seat> seats;
    ExpirationTime expirationTime;
}
