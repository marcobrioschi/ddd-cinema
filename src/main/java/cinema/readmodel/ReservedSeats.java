package cinema.readmodel;

import cinema.domain.Seat;
import cinema.events.Event;
import cinema.query.AskForReservedSeats;
import lombok.Value;

import java.util.List;
import java.util.UUID;

public class ReservedSeats {

    public ReservedSeats(List<Event> eventStore) {
        if (eventStore != null) {
            for (Event event : eventStore) {
                apply(event);
            }
        }
    }

    private void apply(Event event) {
    }

    public QueryResult askForReservedSeats(AskForReservedSeats query) {
        return null;
    }

    @Value
    public static class ReservedSeatsResults implements QueryResult {
        UUID Planned_Screening_ID;
        Seat Seat_A1;
    }

}
