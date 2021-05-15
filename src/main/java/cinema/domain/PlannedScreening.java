package cinema.domain;

import cinema.events.*;

import java.time.LocalDateTime;
import java.util.*;

public class PlannedScreening {

    PlannedScreeningStatus status;

    public PlannedScreening(PlannedScreeningStatus status) {
        this.status = status;
    }

    public static List<Event> createPlannedScreen(Movie movie, SchedulingTime schedulingTime, Room room, UUID uuid) {
        return Arrays.asList(
                new PlannedScreeningCreated(
                        uuid,
                        movie,
                        schedulingTime,
                        room
                )
        );
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

    public List<Event> confirmReservation(UUID reservationId, LocalDateTime now) {
        Reservation reservation = status.getReservation(reservationId);
        if (reservation.getExpirationTime().getLocalDateTime().isAfter(now)) {
            return Arrays.asList(
                    new ReservationConfirmed(status.getId(), reservationId) // TODO: add fields (enrich event)
            );
        } else {
            return Arrays.asList(
                    new ConfirmFailed(
                            status.getId(),
                            reservationId,
                            reservation.getCustomer(),
                            reservation.getSeats(),
                            RefusedConfirmationReasons.RESERVATION_WAS_EXPIRED
                    )
            );
        }
    }

    private boolean theReservationIsStillOpen(LocalDateTime frozenNow) {
        return (status.getSchedulingTime().remainingTimeBeforeStart(frozenNow) > LAST_RESERVATION_WINDOW_MINUTES);
    }

    private boolean checkIfSeatsAreAvailable(List<Seat> seats) {
        for(Seat seat : seats) {
            if(status.seatIsReserved(seat)) {
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
        private Map<UUID, Reservation> pendingReservations = new HashMap<>();

        public PlannedScreeningStatus(List<Event> events) {
            if (events != null) {
                for (Event event : events) {
                    applyEvent(event);
                }
            }
        }

        private void applyEvent(Event event) {
            if (event instanceof PlannedScreeningCreated) {
                PlannedScreeningCreated plannedScreeningCreated = (PlannedScreeningCreated) event;
                this.id = plannedScreeningCreated.getId();
                this.schedulingTime = plannedScreeningCreated.getSchedulingTime();
                return;
            }
            if(event instanceof SeatsReserved) {
                SeatsReserved seatsReserved = (SeatsReserved) event;
                this.reservedSeats.addAll(seatsReserved.getSeats());
                this.pendingReservations.put(
                    seatsReserved.getReservationId(),
                    new Reservation(
                            seatsReserved.getReservationId(),
                            seatsReserved.getCustomer(),
                            seatsReserved.getSeats(),
                            seatsReserved.getExpirationTime()
                    )
                );
                return;
            }
            if (event instanceof ConfirmFailed) {
                ConfirmFailed confirmFailed = (ConfirmFailed) event;
                this.reservedSeats.removeAll(confirmFailed.getSeats());
                this.pendingReservations.remove(confirmFailed.getReservationId());
            }
        }

        public UUID getId() {
            return id;
        }

        public SchedulingTime getSchedulingTime() {
            return schedulingTime;
        }

        private boolean seatIsReserved(Seat seat) {
            return reservedSeats.contains(seat);
        }

        public Reservation getReservation(UUID reservationId) {
            return pendingReservations.get(reservationId);
        }

    }

    private static final int RESERVATION_EXPIRATION_MINUTES = 12;
    private static final int LAST_RESERVATION_WINDOW_MINUTES = 15;

}
