package cinema.readmodel.movielist;

import cinema.events.Event;
import cinema.events.PlannedScreeningCreated;
import cinema.readmodel.QueryResult;
import cinema.readmodel.ReadModel;
import cinema.readmodel.ReadModelID;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MovieList extends ReadModel {

    private List<MovieListEntry> movies = new ArrayList<>();

    @Override
    public ReadModelID getId() {
        return ReadModelID.MovieList;
    }

    @Override
    protected void apply(Event event) {
        if (event instanceof PlannedScreeningCreated) {
            PlannedScreeningCreated plannedScreeningCreated = (PlannedScreeningCreated) event;
            movies.add(
                    new MovieListEntry(
                            plannedScreeningCreated.getId(),
                            plannedScreeningCreated.getSchedulingTime(),
                            plannedScreeningCreated.getMovie()
                    )
            );
        }
    }

    public QueryResult asksMoviesInATimeWindow(AsksMoviesInATimeWindow query) {
        List<MovieListEntry> results = new ArrayList<>();
        for (MovieListEntry movieListEntry : movies) {
            LocalDateTime movieDateTime = movieListEntry.getTiming().getLocalDateTime();
            if (isTheMovieInTheTimeWindow(movieDateTime, query)) {
                results.add(movieListEntry);
            }
        }
        return new MovieListAnswer(results);
    }

    private boolean isTheMovieInTheTimeWindow(LocalDateTime movieDateTime, AsksMoviesInATimeWindow query) {
        return movieDateTime.isAfter(query.getStartFrom()) && movieDateTime.isBefore(query.getStartTo());
    }

}
