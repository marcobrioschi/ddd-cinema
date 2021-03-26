package cinema.domain;

import lombok.Value;

@Value
public class Room {
    String name;

    public Room(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
