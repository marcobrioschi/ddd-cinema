package cinema.infrastructure;

import cinema.events.Event;

import java.util.List;

public interface EventPublisher {

    void publish(List<Event> eventList);

}
