package cinema.infrastructure;

import cinema.events.Event;
import cinema.infrastructure.EventPublisher;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTestEventPublisher implements EventPublisher {

    public List<Event> events = new ArrayList<>();

    @Override
    public void publish(List<Event> eventList) {
        events.addAll(eventList);
    }

}

