package testframework;

import cinema.command.Command;
import cinema.events.Event;
import cinema.infrastructure.CommandHandler;
import cinema.infrastructure.FrozenClock;
import cinema.infrastructure.InMemoryTestEventPublisher;
import cinema.infrastructure.LocalClock;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BDDBaseTest {

    private List<Event> history;
    private InMemoryTestEventPublisher eventPusher;
    private LocalClock localClock;

    protected void Given(Event... events) {
        this.history = Arrays.asList(events);
    }

    protected void When(LocalDateTime frozenTime, Command command) {
        localClock = new FrozenClock(frozenTime);
        eventPusher = new InMemoryTestEventPublisher();
        CommandHandler commandHandler = new CommandHandler(history, localClock, eventPusher);
        commandHandler.handle(command);
    }

    protected void Then(Event... events) {
        assertThat(eventPusher.events, is(Arrays.asList(events)));
    }

}
