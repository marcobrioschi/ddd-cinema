package cinema.infrastructure;

import cinema.command.Command;
import cinema.command.ReserveSeats;
import cinema.domain.PlannedScreening;
import cinema.events.Event;

import java.util.List;

public class CommandHandler {

    private final PlannedScreeningRepository plannedScreeningRepository;
    private final LocalClock localClock;

    public CommandHandler(PlannedScreeningRepository plannedScreeningRepository, LocalClock localClock) {
        this.plannedScreeningRepository = plannedScreeningRepository;
        this.localClock = localClock;
    }

    public void handle(Command command) {
        if (command instanceof ReserveSeats) {
            ReserveSeats reserveSeats = (ReserveSeats)command;
            List<Event> currentEntityHistory = plannedScreeningRepository.loadPlannedScreeningEvents(reserveSeats.getPlannedScreeningId());
            PlannedScreening.PlannedScreeningStatus plannedScreeningStatus = new PlannedScreening.PlannedScreeningStatus(currentEntityHistory);
            PlannedScreening plannedScreening = new PlannedScreening(plannedScreeningStatus);
            List<Event> publishedEvents = plannedScreening.reserveSeats(reserveSeats.getCustomer(), reserveSeats.getSeats(), localClock.now());
            // TODO NOTE: in Marco H. example is the aggregate that pushes the events. In his solution how the command handler can be a 'unit of work'?
            plannedScreeningRepository.persistPlannedScreeningEvents(publishedEvents);
        }
    }

}
