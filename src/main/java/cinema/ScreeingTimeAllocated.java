package cinema;

import cinema.domain.Room;


public class ScreeingTimeAllocated extends Event {

    public final Room room;

    public ScreeingTimeAllocated(Room room) {
        this.room = room;
    }
}
