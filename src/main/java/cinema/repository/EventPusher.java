package cinema.repository;

import cinema.events.Event;

import java.util.List;

public interface EventPusher {

    void push(List<Event> eventList);

}
