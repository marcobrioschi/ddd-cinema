package cinema;

import cinema.domain.PlannedScreening;
import cinema.events.Event;
import cinema.repository.EventPusher;

import java.util.List;

public class CommandHandler {

    private final List<Event> eventStore;
    private final EventPusher eventPusher;

    public CommandHandler(List<Event> eventStore, EventPusher eventPusher) {
        this.eventStore = eventStore;
        this.eventPusher = eventPusher;
    }

    public void handle(ReservationCommand reservationCommand) {

        PlannedScreening plannedScreening = new PlannedScreening(eventStore); //_screeningTimes.findByUUID(reservationCommand.getScreeningTime().getId());
        List<Event> publishedEvents = plannedScreening.reserveSeats(reservationCommand.getCustomer(), reservationCommand.getSeats());
        eventPusher.push(publishedEvents);   // TODO in Marco H. example is the aggregate that pushes the events. In his solution how the command handler can be a 'unit of work'?

    }

}
