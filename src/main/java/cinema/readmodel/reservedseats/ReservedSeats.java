package cinema.readmodel.reservedseats;

import cinema.events.Event;
import cinema.events.SeatsReserved;
import cinema.readmodel.QueryResult;
import cinema.readmodel.ReadModel;
import cinema.readmodel.ReadModelID;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReservedSeats extends ReadModel {

    List<ReservedSeatsEntry> reservations = new ArrayList<>();

    @Override
    public ReadModelID getId() {
        return ReadModelID.ReservedSeats;
    }

    @Override
    protected void apply(Event event) {
        if (event instanceof SeatsReserved) {
            SeatsReserved seatsReservedEvent = (SeatsReserved)event;
            reservations.add(
                    new ReservedSeatsEntry(seatsReservedEvent.getAggregateId(), seatsReservedEvent.getCustomer(), seatsReservedEvent.getSeats())
            );
            return;
        }
    }

    public QueryResult askForReservedSeats(AskForReservedSeats query) {
        List<ReservedSeatsEntry> entries = reservations.stream().filter(entry -> entry.getCustomer().equals(query.getCustomer())).collect(Collectors.toList());
        return new ReservedSeatsAnswer(
                entries
        );
    }

}
