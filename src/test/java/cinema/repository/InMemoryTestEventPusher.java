package cinema.repository;

import cinema.events.Event;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTestEventPusher implements EventPusher {

    private List<Event> events = new ArrayList<>();

    @Override
    public void push(List<Event> eventList) {
        events.addAll(eventList);
    }

    public List<Event> getEvents() {
        return events;
    }

}

