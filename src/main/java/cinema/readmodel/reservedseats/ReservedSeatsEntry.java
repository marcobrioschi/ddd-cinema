package cinema.readmodel.reservedseats;

import cinema.domain.Customer;
import cinema.domain.Seat;
import lombok.Value;

import java.util.List;
import java.util.UUID;

// TODO: aggiungere movie e scheudlazione

@Value
public class ReservedSeatsEntry {
    UUID id;
    Customer customer;
    List<Seat> seats;
}
