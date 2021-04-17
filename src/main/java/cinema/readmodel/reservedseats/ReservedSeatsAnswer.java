package cinema.readmodel.reservedseats;

import cinema.readmodel.QueryResult;
import lombok.Value;

import java.util.List;

@Value
public class ReservedSeatsAnswer implements QueryResult {
    List<ReservedSeatsEntry> reservations;
}
