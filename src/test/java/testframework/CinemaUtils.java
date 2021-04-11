package testframework;

import cinema.command.ReserveSeats;
import cinema.domain.*;
import cinema.events.*;

import java.util.List;
import java.util.UUID;

public class CinemaUtils {

    // Commands
    public static ReserveSeats ReservationCommand(Customer customer, UUID plannedScreeningId, List<Seat> seats) {
        return new ReserveSeats(customer, plannedScreeningId, seats);
    }

    // Events
    public static PlannedScreeningCreated PlannedScreeningCreated(UUID id, Movie movie, SchedulingTime schedulingTime, Room room) {
        return new PlannedScreeningCreated(id, movie, schedulingTime, room);
    }

    public static SeatsReserved SeatsReserved(Customer customer, List<Seat> seats, UUID screeningTimeId, ExpirationTime expirationTime) {
        return new SeatsReserved(screeningTimeId, customer, seats, expirationTime);
    }

    public static ReservationFailed ReservationFailed(UUID id, Customer customer, List<Seat> seats, RefusedReservationReasons reason) {
        return new ReservationFailed(id, customer, seats, reason);
    }

}
