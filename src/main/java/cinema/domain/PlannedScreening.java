package cinema.domain;

import cinema.events.*;

import java.time.LocalDateTime;
import java.util.*;

public class PlannedScreening {

    PlannedScreeningStatus status;

    public PlannedScreening(PlannedScreeningStatus status) {
        this.status = status;
    }

    public List<Event> reserveSeats(Customer customer, List<Seat> seats, Now reservationTime) {
        if (!theReservationIsStillOpen(reservationTime)) {
            return Arrays.asList(new ReservationFailed(status.getId(), customer, seats, RefusedReservationReasons.RESERVATION_TOO_CLOSE_TO_SCREENING_START));
        }
        if (!checkIfSeatsAreAvailable(seats)) {
            return Arrays.asList(new ReservationFailed(status.getId(), customer, seats, RefusedReservationReasons.SEATS_ALREADY_RESERVED));
        }
        return Arrays.asList(new SeatsReserved(status.getId(), customer, seats, calculateReservationExpirationTime(reservationTime)));
    }

    private boolean theReservationIsStillOpen(Now reservationTime) {
        LocalDateTime lastReservationTime = status.getSchedulingTime().getLocalDateTime().minusMinutes(LAST_RESERVATION_WINDOW_MINUTES);
        return (reservationTime.getNow().isBefore(lastReservationTime));    // TODO: manage the time without look in the value objects fields
    }

    private boolean checkIfSeatsAreAvailable(List<Seat> seats) {
        for(Seat seat : seats) {
            if(status.getReservedSeats().contains(seat)) {
                return false;
            }
        }
        return true;
    }

    private ExpirationTime calculateReservationExpirationTime(Now reservationTime) {
        return new ExpirationTime(reservationTime.getNow().plusMinutes(RESERVATION_EXPIRATION_MINUTES));
    }

    public static class PlannedScreeningStatus {

        private UUID id;
        private SchedulingTime schedulingTime;
        private List<Seat> reservedSeats = new ArrayList<>();

        public PlannedScreeningStatus(List<Event> events) {
            if (events != null) {
                for (Event event : events) {
                    applyEvent(event);
                }
            }
        }

        private void applyEvent(Event event) {
            if (event instanceof PlannedScreeningCreated) {
                this.id = ((PlannedScreeningCreated)event).getId();
                this.schedulingTime = ((PlannedScreeningCreated)event).getSchedulingTime();
                return;
            }
            if(event instanceof SeatsReserved) {
                this.reservedSeats.addAll(((SeatsReserved)event).getSeats());
                return;
            }
        }

        public UUID getId() {
            return id;
        }

        public SchedulingTime getSchedulingTime() {
            return schedulingTime;
        }

        public List<Seat> getReservedSeats() {
            return Collections.unmodifiableList(reservedSeats);
        }

    }

    private static final int RESERVATION_EXPIRATION_MINUTES = 12;
    private static final int LAST_RESERVATION_WINDOW_MINUTES = 15;

}
