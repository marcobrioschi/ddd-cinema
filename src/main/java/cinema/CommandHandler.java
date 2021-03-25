package cinema;

import cinema.domain.ScreeningTime;

public class CommandHandler {

    private final ScreeningTimes _screeningTimes;

    public CommandHandler(ScreeningTimes screeningTimes) {
        this._screeningTimes = screeningTimes;
    }

    public void handle(ReservationCommand reservationCommand) {
        ScreeningTime screeningTime = _screeningTimes.findByUUID(reservationCommand.getScreeningTime().getId());
        screeningTime.reserveSeats(reservationCommand.getCustomer(), reservationCommand.getSeats());
        _screeningTimes.save(screeningTime);
    }

}
