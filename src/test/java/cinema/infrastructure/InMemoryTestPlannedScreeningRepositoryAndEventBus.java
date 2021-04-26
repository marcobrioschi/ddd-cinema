package cinema.infrastructure;

import cinema.events.Event;
import cinema.readmodel.ReadModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class InMemoryTestPlannedScreeningRepositoryAndEventBus implements PlannedScreeningRepository {

    private final List<Event> history;
    private final List<ReadModel> readModels;
    private List<Event> testPublishedEvents = new ArrayList<>();

    public InMemoryTestPlannedScreeningRepositoryAndEventBus(List<Event> history, List<ReadModel> readModels) {
        this.history = new ArrayList<>(history);
        this.readModels = readModels;
    }

    @Override
    public List<Event> loadPlannedScreeningEvents(UUID id) {
        return Collections.unmodifiableList(
                history.stream()
                        .filter(
                                event -> event.getAggregateRootId().equals(id)
                        )
                        .collect(Collectors.toList())
        );
    }

    @Override
    public void persistPlannedScreeningEvents(List<Event> eventList) {
        testPublishedEvents.addAll(eventList);
        updateAllReadModels(eventList);
    }

    private void updateAllReadModels(List<Event> eventList) {
        for (ReadModel readModel : readModels) {
            updateOneReadModel(eventList, readModel);
        }
    }

    private void updateOneReadModel(List<Event> eventList, ReadModel readModel) {
        for (Event event : eventList) {
            readModel.onEvent(event);
        }
    }

    public List<Event> getTestPublishedEvents() {
        return testPublishedEvents;
    }

}

