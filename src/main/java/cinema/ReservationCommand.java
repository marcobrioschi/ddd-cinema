package cinema;

import cinema.domain.Customer;
import cinema.domain.ScreeningTime;
import cinema.domain.Seat;
import lombok.Value;

import java.util.List;

@Value
public class ReservationCommand {

    Customer customer;
    ScreeningTime screeningTime;
    List<Seat> seats;

}
