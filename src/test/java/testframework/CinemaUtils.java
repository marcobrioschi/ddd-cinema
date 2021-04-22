package testframework;

import cinema.command.ReserveSeats;
import cinema.domain.*;
import cinema.events.PlannedScreeningCreated;
import cinema.events.ReservationFailed;
import cinema.events.SeatsReserved;
import cinema.readmodel.movielist.AsksMoviesInATimeWindow;
import cinema.readmodel.movielist.MovieListAnswer;
import cinema.readmodel.movielist.MovieListEntry;
import cinema.readmodel.reservedseats.AskForReservedSeats;
import cinema.readmodel.reservedseats.ReservedSeatsAnswer;
import cinema.readmodel.reservedseats.ReservedSeatsEntry;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class CinemaUtils {

    // Commands
    public static ReserveSeats ReserveSeats(Customer customer, UUID plannedScreeningId, List<Seat> seats) {
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

    public static ReservedSeatsEntry ReservedSeatsEntry(UUID uuid, Customer customer, SchedulingTime schedulingTime, Movie movie, List<Seat> seats) {
        return new ReservedSeatsEntry(uuid, customer, schedulingTime, movie, seats);
    }

}
