package cinema.domain;

import lombok.Value;

@Value
public class Movie {
    String title;

    public Movie(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
