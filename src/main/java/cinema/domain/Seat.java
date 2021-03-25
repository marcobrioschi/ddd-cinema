package cinema.domain;

import lombok.Value;

@Value
public class Seat {
    Room room;
    String row;
    int number;
}
