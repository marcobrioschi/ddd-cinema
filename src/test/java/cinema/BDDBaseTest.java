package cinema;

import cinema.command.Command;
import cinema.events.Event;
import cinema.repository.InMemoryTestEventPusher;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

// TODO this is only a way to share code, there is a better solution?
public class BDDBaseTest {

    private List<Event> history; // TODO create eventrepository interface also for the store part
    private InMemoryTestEventPusher eventPusher;

    protected void Given(Event... events) {
        this.history = Arrays.asList(events);
    }

    protected void When(Command command) {
        eventPusher = new InMemoryTestEventPusher();
        CommandHandler commandHandler = new CommandHandler(history, eventPusher);
        commandHandler.handle(command);
    }

    protected void Then(Event... events) {
        assertThat(eventPusher.getEvents(), is(Arrays.asList(events)));
    }

}
