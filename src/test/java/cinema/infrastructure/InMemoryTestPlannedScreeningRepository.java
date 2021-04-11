package cinema.infrastructure;

import cinema.events.Event;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryTestPlannedScreeningRepository implements PlannedScreeningRepository {

    private final List<Event> history;
    private List<Event> testPublishedEvents = new ArrayList<>();

    public InMemoryTestPlannedScreeningRepository(List<Event> history) {
        this.history = new ArrayList<>(history);
    }

    @Override
    public List<Event> loadPlannedScreeningEvents(UUID id) {
        return Collections.unmodifiableList(
                history.stream()
                        .filter(
                                event -> event.getAggregateId().equals(id)
                        )
                        .collect(Collectors.toList())
        );
    }

    @Override
    public void persistPlannedScreeningEvents(List<Event> eventList) {
        history.addAll(eventList);
        testPublishedEvents.addAll(eventList);
    }

    public List<Event> getTestPublishedEvents() {
        return testPublishedEvents;
    }

    public List<Event> loadFullHistory() {
        return Collections.unmodifiableList(history);
    }

}

