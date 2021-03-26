package cinema.domain;

import cinema.Event;
import cinema.ScreeingTimeAllocated;
import cinema.ScreeningTimeCreated;
import cinema.ScreeningTimeScheduled;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
public class ScreeningTime {

    UUID id;
    Movie movie;
    Room room;
    SchedulingTime schedulingTime;
    List<Seat> reservedSeats;

    public ScreeningTime(List<Event> events) {
        if (events != null) {
            reservedSeats = new ArrayList<>();
            for (Event event : events) {
                apply(event);
            }
        }
    }

    private void apply(Event event) {
        if (event instanceof ScreeningTimeCreated) {
            this.id = ((ScreeningTimeCreated)event).id;
            return;
        }
        if (event instanceof ScreeningTimeScheduled) {
            this.schedulingTime = ((ScreeningTimeScheduled)event).schedulingTime;
            this.movie = ((ScreeningTimeScheduled)event).movie;
            return;
        }
        if (event instanceof ScreeingTimeAllocated) {
            this.room = ((ScreeingTimeAllocated)event).room;
            return;
        }
    }

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
