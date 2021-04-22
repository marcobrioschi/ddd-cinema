package cinema.readmodel.reservedseats;

import cinema.domain.Movie;
import cinema.domain.SchedulingTime;
import cinema.events.Event;
import cinema.events.PlannedScreeningCreated;
import cinema.events.SeatsReserved;
import cinema.readmodel.QueryResult;
import cinema.readmodel.ReadModel;
import cinema.readmodel.ReadModelID;

import java.util.*;
import java.util.stream.Collectors;

public class ReservedSeats extends ReadModel {
    Map<UUID, SchedulingTime> scheduling = new Hashtable<>();
    Map<UUID, Movie> movies = new Hashtable<>();
    List<ReservedSeatsEntry> reservations = new ArrayList<>();

    @Override
    public ReadModelID getId() {
        return ReadModelID.ReservedSeats;
    }

    @Override
    protected void apply(Event event) {
        if (event instanceof PlannedScreeningCreated) {
            PlannedScreeningCreated plannedScreeningCreated = (PlannedScreeningCreated)event;
            UUID plannedScreeningCreatedId = plannedScreeningCreated.getId();
            scheduling.put(plannedScreeningCreatedId, plannedScreeningCreated.getSchedulingTime());
            movies.put(plannedScreeningCreatedId, plannedScreeningCreated.getMovie());
            return;
        }
        if (event instanceof SeatsReserved) {
            SeatsReserved seatsReservedEvent = (SeatsReserved)event;
            UUID seatsReservedEventId = seatsReservedEvent.getAggregateId();
            reservations.add(
                    new ReservedSeatsEntry(
                            seatsReservedEventId,
                            seatsReservedEvent.getCustomer(),
                            scheduling.get(seatsReservedEventId),
                            movies.get(seatsReservedEventId),
                            seatsReservedEvent.getSeats()
                    )
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
