package cinema;

import cinema.domain.ScreeningTime;

import java.util.List;

public class CommandHandler {

    private final ScreeningTimes _screeningTimes;

    public CommandHandler(ScreeningTimes screeningTimes) {
        this._screeningTimes = screeningTimes;
    }

    public List<Event> handle(ReservationCommand reservationCommand) {
        ScreeningTime screeningTime = _screeningTimes.findByUUID(reservationCommand.getScreeningTime().getId());
        List<Event> result = screeningTime.reserveSeats(reservationCommand.getCustomer(), reservationCommand.getSeats());
        _screeningTimes.save(screeningTime);

        return result;
    }

}
