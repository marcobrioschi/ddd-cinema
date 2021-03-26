package cinema;

import cinema.domain.Customer;
import cinema.domain.Seat;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
public class ReservationCommand {
    Customer customer;
    UUID plannedScreeningId;
    List<Seat> seats;
}
