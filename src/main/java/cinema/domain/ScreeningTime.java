package cinema.domain;

import lombok.Data;
import lombok.Getter;

import java.util.Date;
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
        // TODO what I'll do with the reservation object?
        // TODO the entity is included in the value object
        Reservation reservation =  new Reservation(customer, seats, this, new ExpirationTime(new Date()));
    }

}
