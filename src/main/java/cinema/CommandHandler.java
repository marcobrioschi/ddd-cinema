package cinema;

import cinema.domain.ScreeningTime;
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

    public List<Event> handle(ReservationCommand reservationCommand) {

        ScreeningTime screeningTime = new ScreeningTime(eventStore); //_screeningTimes.findByUUID(reservationCommand.getScreeningTime().getId());
        List<Event> result = screeningTime.reserveSeats(reservationCommand.getCustomer(), reservationCommand.getSeats());
        eventPusher.push(result);   // TODO in Marco H. example is the aggregate that pushes the events. In his solution how the command handler can be a 'unit of work'?
        return result;
    }

}
