package cinema.domain;

import lombok.Value;

import java.util.List;

@Value
public class Reservation {
    Customer customer;
    List<Seat> seats;
    ScreeningTime screeningTime;
    ExpirationTime expirationTime;
}
