package cinema.infrastructure;

import cinema.command.Command;
import cinema.command.ReservationCommand;
import cinema.domain.PlannedScreening;
import cinema.events.Event;

import java.util.List;

public class CommandHandler {

    private final List<Event> eventStore;
    private final EventPublisher eventPublisher;
    private final LocalClock localClock;

    // TODO create eventrepository interface also for the store part
    public CommandHandler(List<Event> eventStore, LocalClock localClock, EventPublisher eventPublisher) {
        this.eventStore = eventStore;
        this.eventPublisher = eventPublisher;
        this.localClock = localClock;
    }

    public void handle(Command command) {
        if (command instanceof  ReservationCommand) {
            ReservationCommand reservationCommand = (ReservationCommand)command;
            PlannedScreening plannedScreening = new PlannedScreening(eventStore); // TODO: filter events _screeningTimes.findByUUID(command.getScreeningTime().getId());
            List<Event> publishedEvents = plannedScreening.reserveSeats(reservationCommand.getCustomer(), reservationCommand.getSeats(), localClock.now());
            eventPublisher.publish(publishedEvents);   // TODO in Marco H. example is the aggregate that pushes the events. In his solution how the command handler can be a 'unit of work'?
        }
    }

}
