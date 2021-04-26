package cinema.domain;

import cinema.events.Event;
import cinema.events.PlannedScreeningCreated;
import cinema.events.ReservationFailed;
import cinema.events.SeatsReserved;

import java.time.LocalDateTime;
import java.util.*;

public class PlannedScreening {

    PlannedScreeningStatus status;

    public PlannedScreening(PlannedScreeningStatus status) {
        this.status = status;
    }

    public List<Event> reserveSeats(Customer customer, List<Seat> seats, LocalDateTime frozenNow, UUID reservationId) {
        if (!theReservationIsStillOpen(frozenNow)) {
            return Arrays.asList(new ReservationFailed(status.getId(), customer, seats, RefusedReservationReasons.RESERVATION_TOO_CLOSE_TO_SCREENING_START));
        }
        if (!checkIfSeatsAreAvailable(seats)) {
            return Arrays.asList(new ReservationFailed(status.getId(), customer, seats, RefusedReservationReasons.SEATS_ALREADY_RESERVED));
        }
        return Arrays.asList(
                new SeatsReserved(
                        status.getId(),
                        reservationId,
                        customer,
                        seats,
                        calculateReservationExpirationTime(frozenNow)
                )
        );
    }

    private boolean theReservationIsStillOpen(LocalDateTime frozenNow) {
        return (status.getSchedulingTime().remainingTimeBeforeStart(frozenNow) > LAST_RESERVATION_WINDOW_MINUTES);
    }

    private boolean checkIfSeatsAreAvailable(List<Seat> seats) {
        for(Seat seat : seats) {
            if(status.getReservedSeats().contains(seat)) {
                return false;
            }
        }
        return true;
    }

    private ExpirationTime calculateReservationExpirationTime(LocalDateTime frozenNow) {
        return new ExpirationTime(frozenNow.plusMinutes(RESERVATION_EXPIRATION_MINUTES));
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
