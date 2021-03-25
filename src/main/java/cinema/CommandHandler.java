package cinema;

public class CommandHandler {

    public void handle(ReservationCommand reservationCommand) {
        ScreeningTime screeningTime = getScreeningTimes(reservationCommand.screeningTime);
        screeningTime.reservedSeats(reservationCommand.seats, reservationCommand.customer);
        
    }

    private ScreeningTime getScreeningTimes(ScreeningTime screeningTime) {
        return null;
    }
}
