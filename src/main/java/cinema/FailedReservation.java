package cinema;

import cinema.domain.Customer;
import cinema.domain.Seat;

import java.util.List;

public class FailedReservation extends Event {

    public final String reason;
    public final List<Seat> seats;
    public final Customer customer;

    public FailedReservation(String reason, List<Seat> seats, Customer customer) {
        this.reason = reason;
        this.seats = seats;
        this.customer = customer;
    }
}
