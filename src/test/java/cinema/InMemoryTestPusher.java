package cinema;

import cinema.events.Event;
import cinema.repository.EventPusher;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTestPusher implements EventPusher {

    private List<Event> events = new ArrayList<>();

    @Override
    public void push(List<Event> eventList) {
        events.addAll(eventList);
    }

    public List<Event> getEvents() {
        return events;
    }

}

