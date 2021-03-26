package cinema;

import cinema.domain.Customer;
import cinema.domain.ExpirationTime;
import cinema.domain.Seat;

import java.util.List;
import java.util.UUID;

public class SeatsReserved extends Event {

    public final Customer customer;
    public final List<Seat> seats;
    public final UUID id;
    public final ExpirationTime expirationTime;

    public SeatsReserved(Customer customer, List<Seat> seats, UUID id, ExpirationTime expirationTime) {
        this.customer = customer;
        this.seats = seats;
        this.id = id;
        this.expirationTime = expirationTime;
    }
}
