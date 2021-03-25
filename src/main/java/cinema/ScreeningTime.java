package cinema;

import java.util.List;
import java.util.UUID;

public class ScreeningTime {
    public UUID id;
    public Movie movie;
    public Room room;
    public SchedulingTime schedulingTime;


    public void reservedSeats(List<Seat> seats, Customer customer) {
    }
}
