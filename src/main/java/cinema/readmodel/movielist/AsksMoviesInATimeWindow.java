package cinema.readmodel.movielist;

import cinema.readmodel.Query;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class AsksMoviesInATimeWindow implements Query {
    LocalDateTime startFrom;
    LocalDateTime startTo;
}
