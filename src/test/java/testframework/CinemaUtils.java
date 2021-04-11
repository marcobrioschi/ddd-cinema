package testframework;

import cinema.domain.*;
import cinema.events.*;

import java.util.List;
import java.util.UUID;

public class CinemaUtils {

    public static PlannedScreeningCreated PlannedScreeningCreated(UUID id) {
        return new PlannedScreeningCreated(id);
    }

    public static PlannedScreeningScheduled PlannedScreeningScheduled(Movie movie, SchedulingTime schedulingTime) {
        return new PlannedScreeningScheduled(movie, schedulingTime);
    }

    public static PlannedScreeingAllocated PlannedScreeingAllocated(Room room) {
        return new PlannedScreeingAllocated(room);
    }

    public static SeatsReserved SeatsReserved(Customer customer, List<Seat> seats, UUID screeningTimeId, ExpirationTime expirationTime) {
        return new SeatsReserved(customer, seats, screeningTimeId, expirationTime);
    }

    public static ReservationFailed ReservationFailed(Customer customer, List<Seat> seats, RefusedReservationReasons reason) {
        return new ReservationFailed(customer, seats, reason);
    }

}
