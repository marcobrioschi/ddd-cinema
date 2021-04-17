package testframework;

import cinema.command.Command;
import cinema.events.Event;
import cinema.infrastructure.CommandHandler;
import cinema.infrastructure.FrozenClock;
import cinema.infrastructure.InMemoryTestPlannedScreeningRepositoryAndEventBus;
import cinema.infrastructure.QueryHandler;
import cinema.readmodel.Query;
import cinema.readmodel.QueryResult;
import cinema.readmodel.ReadModel;
import cinema.readmodel.movielist.MovieList;
import cinema.readmodel.reservedseats.ReservedSeats;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

// TODO: make this class indepenent from the domain
public class BDDBaseTest {

    private List<ReadModel> readModels;
    private InMemoryTestPlannedScreeningRepositoryAndEventBus inMemoryTestPlannedScreeningRepositoryAndEventBus;
    private QueryResult currentQueryResult;

    protected void Given(Event... events) {
        List<Event> history = new ArrayList(Arrays.asList(events));
        this.readModels = Arrays.asList(
                new MovieList(),
                new ReservedSeats()
        );
        for (ReadModel readModel : readModels) {
            readModel.feedHistory(history);
        }
        this.inMemoryTestPlannedScreeningRepositoryAndEventBus =
                new InMemoryTestPlannedScreeningRepositoryAndEventBus(history, readModels);
    }

    protected void When(LocalDateTime frozenTime, Command command) {
        CommandHandler commandHandler = new CommandHandler(
                inMemoryTestPlannedScreeningRepositoryAndEventBus,
                new FrozenClock(frozenTime));
        commandHandler.handle(command);
    }

    protected void Then(Event... events) {
        assertThat(inMemoryTestPlannedScreeningRepositoryAndEventBus.getTestPublishedEvents(), is(Arrays.asList(events)));
    }

    protected void Query(Query query) {
        QueryHandler queryHandler = new QueryHandler(readModels);
        currentQueryResult = queryHandler.handle(query);
    }

    protected void Then(QueryResult expectedQueryResult) {
        assertThat(this.currentQueryResult, is(expectedQueryResult));
    }

}
