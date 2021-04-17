package cinema.readmodel;

import cinema.events.Event;

import java.util.List;

public abstract class ReadModel {

    public abstract ReadModelID getId();

    public final void feedHistory(List<Event> history) {
        if (history != null) {
            for (Event event : history) {
                apply(event);
            }
        }
    }

    public final void onEvent(Event event) {
        apply(event);
    }

    protected abstract void apply(Event event);

}
