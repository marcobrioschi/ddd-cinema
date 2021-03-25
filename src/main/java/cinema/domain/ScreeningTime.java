package cinema.domain;

import lombok.Data;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class ScreeningTime {

    final UUID id;
    Movie movie;
    Room room;
    SchedulingTime schedulingTime;
    List<Seat> reservedSeats;

    public ScreeningTime(UUID id, Movie movie, Room room, SchedulingTime schedulingTime, List<Seat> reservedSeats) {
        this.id = id;
        this.movie = movie;
        this.room = room;
        this.schedulingTime = schedulingTime;
        this.reservedSeats = reservedSeats;
    }

    public void reserveSeats(Customer customer, List<Seat> seats) {
        reservedSeats.addAll(seats);
    }

}
