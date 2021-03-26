package cinema.domain;

import cinema.*;
import lombok.Getter;

import java.util.*;

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

    public ScreeningTime(UUID id, Movie movie, Room room, SchedulingTime schedulingTime, List<Seat> reservedSeats) {
        this.id = id;
        this.movie = movie;
        this.room = room;
        this.schedulingTime = schedulingTime;
        this.reservedSeats = reservedSeats;
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

    public List<Event> reserveSeats(Customer customer, List<Seat> seats) {
        // reservedSeats.addAll(seats);
        // TODO what I'll do with the reservation object?
        // TODO the entity is included in the value object
        // Reservation reservation =  new Reservation(customer, seats, this, new ExpirationTime(new Date()));
        // TODO BL
        Event _event = new SeatsReserved(customer, seats, id, new ExpirationTime(new Date()));
        return Arrays.asList(_event);
    }

    public UUID getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    public Room getRoom() {
        return room;
    }

    public SchedulingTime getSchedulingTime() {
        return schedulingTime;
    }

    public List<Seat> getReservedSeats() {
        return reservedSeats;
    }
}
