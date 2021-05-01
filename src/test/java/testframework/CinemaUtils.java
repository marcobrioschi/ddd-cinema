package testframework;

import cinema.command.ConfirmReservation;
import cinema.command.CreatePlannedScreening;
import cinema.command.ReserveSeats;
import cinema.domain.*;
import cinema.events.*;
import cinema.readmodel.movielist.AsksMoviesInATimeWindow;
import cinema.readmodel.movielist.MovieListAnswer;
import cinema.readmodel.movielist.MovieListEntry;
import cinema.readmodel.reservedseats.AskForReservedSeats;
import cinema.readmodel.reservedseats.ReservedSeatsAnswer;
import cinema.readmodel.reservedseats.ReservedSeatsEntry;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static testframework.TestScenario.Planned_Screening_ID1;
import static testframework.TestScenario.Reservation_ID1;

public class CinemaUtils {

    // Commands
    public static ReserveSeats ReserveSeats(Customer customer, UUID plannedScreeningId, List<Seat> seats) {
        return new ReserveSeats(plannedScreeningId, customer, seats);
    }

    public static CreatePlannedScreening CreatePlannedScreening(Movie movie, SchedulingTime schedulingTime, Room room) {
        return new CreatePlannedScreening(movie, schedulingTime, room);
    }

    public static ConfirmReservation ConfirmReservation(UUID Planned_Screening_ID1, UUID Reservation_ID1) {
        return new ConfirmReservation(Planned_Screening_ID1, Reservation_ID1);
    }

    // Events
    public static PlannedScreeningCreated PlannedScreeningCreated(UUID id, Movie movie, SchedulingTime schedulingTime, Room room) {
        return new PlannedScreeningCreated(id, movie, schedulingTime, room);
    }

    public static SeatsReserved SeatsReserved(UUID screeningTimeId, UUID reservationID, Customer customer, List<Seat> seats, ExpirationTime expirationTime) {
        return new SeatsReserved(screeningTimeId, reservationID, customer, seats, expirationTime);
    }

    public static ReservationFailed ReservationFailed(UUID id, Customer customer, List<Seat> seats, RefusedReservationReasons reason) {
        return new ReservationFailed(id, customer, seats, reason);
    }

    public static ReservationConfirmed ReservationConfirmed(UUID Planned_Screening_ID1, UUID Reservation_ID1) {
        return new ReservationConfirmed(Planned_Screening_ID1, Reservation_ID1);
    }

    public static ConfirmFailed ConfirmFailed(UUID Planned_Screening_ID1, UUID Reservation_ID1, Customer customer, List<Seat> seats, RefusedConfirmationReasons reason) {
        return new ConfirmFailed(Planned_Screening_ID1, Reservation_ID1, customer, seats, reason);
    }

    // Query
    public static AsksMoviesInATimeWindow MovieListInTimeWindow(LocalDateTime startFrom, LocalDateTime startTo) {
        return new AsksMoviesInATimeWindow(startFrom, startTo);
    }

    public static MovieListAnswer MovieListAnswer(List<MovieListEntry> movieList) {
        return new MovieListAnswer(movieList);
    }

    public static MovieListEntry MovieListEntry(UUID uuid , SchedulingTime timing, Movie movie, Room room) {
        return new MovieListEntry(uuid, timing, movie, room);
    }

    public static AskForReservedSeats AskForReservedSeats(Customer customer) {
        return new AskForReservedSeats(customer);
    }

    public static ReservedSeatsAnswer ReservedSeatsAnswer(List<ReservedSeatsEntry> reservations) {
        return new ReservedSeatsAnswer(reservations);
    }

    public static ReservedSeatsEntry ReservedSeatsEntry(UUID uuid, UUID reservationId, Customer customer, SchedulingTime schedulingTime, Movie movie, List<Seat> seats) {
        return new ReservedSeatsEntry(uuid, reservationId, customer, schedulingTime, movie, seats);
    }

}
