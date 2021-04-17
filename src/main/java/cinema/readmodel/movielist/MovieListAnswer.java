package cinema.readmodel.movielist;

import cinema.readmodel.QueryResult;
import lombok.Value;

import java.util.List;

@Value
public class MovieListAnswer implements QueryResult {
    List<MovieListEntry> movieList;
}
