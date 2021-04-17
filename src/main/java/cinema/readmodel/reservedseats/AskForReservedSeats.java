package cinema.readmodel.reservedseats;

import cinema.domain.Customer;
import cinema.readmodel.Query;
import lombok.Value;

@Value
public class AskForReservedSeats implements Query {
    Customer customer;
}
