package cinema;

import cinema.domain.ScreeningTime;

public class CommandHandler {

    private final ScreeningTime _screeningTime;

    public CommandHandler(ScreeningTime screeningTime) {
        this._screeningTime = screeningTime;
    }

    public void handle(ReservationCommand reservationCommand) {
        _screeningTime.reserveSeats(reservationCommand.getCustomer(), reservationCommand.getSeats());
    }

}
