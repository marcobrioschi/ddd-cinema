package cinema.command;

import cinema.domain.Customer;
import cinema.domain.Seat;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
public class ReserveSeats implements Command {
    UUID plannedScreeningId;
    Customer customer;
    List<Seat> seats;
}
