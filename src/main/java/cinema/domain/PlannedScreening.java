package cinema.domain;

import cinema.events.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Getter
public class PlannedScreening {

    UUID id;
    Movie movie;
    Room room;
    SchedulingTime schedulingTime;
    List<Seat> reservedSeats = new ArrayList<>();

    public PlannedScreening(List<Event> events) {
        if (events != null) {
            for (Event event : events) {
                apply(event);
            }
        }
    }

    private void apply(Event event) {
        if (event instanceof PlannedScreeningCreated) {
            this.id = ((PlannedScreeningCreated)event).id;
            return;
        }
        if (event instanceof PlannedScreeningScheduled) {
            this.movie = ((PlannedScreeningScheduled)event).getMovie();
            this.schedulingTime = ((PlannedScreeningScheduled)event).getSchedulingTime();
            return;
        }
        if (event instanceof PlannedScreeingAllocated) {
            this.room = ((PlannedScreeingAllocated)event).getRoom();
            return;
        }
        if(event instanceof SeatsReserved) {
            this.reservedSeats.addAll(((SeatsReserved)event).getSeats());
            return;
        }
    }

    public List<Event> reserveSeats(Customer customer, List<Seat> seats, Now reservationTime) {
        for(Seat seat : seats) {
            if(reservedSeats.contains(seat)) {
                return Arrays.asList(new ReservationFailed(customer, seats, RefusedReservationReason.SEATS_ALREADY_RESERVED));
            }
        }
        Event _event = new SeatsReserved(customer, seats, id, calculateReservationExpirationTime(reservationTime));
        return Arrays.asList(_event);
    }

    private ExpirationTime calculateReservationExpirationTime(Now reservationTime) {
        return new ExpirationTime(reservationTime.getNow().plusMinutes(RESERVATION_EXPIRATION_TIME));
    }

    private static final int RESERVATION_EXPIRATION_TIME = 12;

}
