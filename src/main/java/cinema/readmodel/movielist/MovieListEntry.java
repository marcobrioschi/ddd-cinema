package cinema.readmodel.movielist;

import cinema.domain.Movie;
import cinema.domain.SchedulingTime;
import lombok.Value;

import java.util.UUID;

@Value
public class MovieListEntry {
    UUID uuid;
    SchedulingTime timing;
    Movie movie;
}
