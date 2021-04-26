package cinema.domain;

import lombok.Value;

import java.util.List;
import java.util.UUID;

// TODO: va rimosso?

@Value
public class Reservation {
    Customer customer;
    List<Seat> seats;
    UUID plannedScreeningId;
    ExpirationTime expirationTime;
}
