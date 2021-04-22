package cinema.readmodel.reservedseats;

import cinema.domain.Customer;
import cinema.domain.Movie;
import cinema.domain.SchedulingTime;
import cinema.domain.Seat;
import lombok.Value;

import java.util.List;
import java.util.UUID;


@Value
public class ReservedSeatsEntry {
    UUID id;
    Customer customer;
    SchedulingTime schedulingTime;
    Movie movie;
    List<Seat> seats;
}
